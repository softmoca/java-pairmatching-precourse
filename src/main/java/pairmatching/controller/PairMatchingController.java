package pairmatching.controller;

import java.util.List;
import java.util.function.Supplier;
import pairmatching.domain.FunctionCommand;
import pairmatching.domain.MatchingHistory;
import pairmatching.domain.MatchingKey;
import pairmatching.domain.Pair;
import pairmatching.domain.RematchCommand;
import pairmatching.service.PairMatchingService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {

    private final InputView inputView;
    private final OutputView outputView;
    private final PairMatchingService pairMatchingService;
    private final MatchingHistory matchingHistory;

    public PairMatchingController(InputView inputView, OutputView outputView, PairMatchingService pairMatchingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pairMatchingService = pairMatchingService;
        this.matchingHistory = new MatchingHistory();
    }

    public void run() {
        while (true) {
            FunctionCommand command = readCommand();
            if (command == FunctionCommand.QUIT) {
                break;
            }
            functioning(command);
        }
    }

    private void functioning(FunctionCommand command) {
        if (command == FunctionCommand.MATCHING) {
            outputView.printCourseAndMission();
            functioningMatching();
            return;
        }

        if (command == FunctionCommand.QUERY) {
            functioningQuery();
            return;
        }

        if (command == FunctionCommand.CLEAR) {
            functioningClear();
        }
    }

    private void functioningClear() {
        matchingHistory.clear();
    }

    private void functioningQuery() {
        while (true) {
            try {
                MatchingKey matchingKey = readMatchingKey();
                List<Pair> pairs = matchingHistory.getPairsByKey(matchingKey);

                if (pairs.isEmpty()) {
                    throw new IllegalArgumentException("[ERROR] 매칭 이력이 없습니다.");
                }

                outputView.printMatchingResult(pairs);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }


    private void functioningMatching() {
        MatchingKey matchingKey = readMatchingKey();

        if (matchingHistory.contains(matchingKey)) {//재매칭
            RematchCommand rematchCommand = readRematcingCommand();
            if (rematchCommand == RematchCommand.YES) {
                matching(matchingKey);         //매칭
                return;
            }
            functioningMatching();
            return;
        }
        matching(matchingKey);
    }

    private void matching(MatchingKey matchingKey) {
        List<Pair> pairs = pairMatchingService.match(matchingKey);
        outputView.printMatchingResult(pairs);
    }

    private RematchCommand readRematcingCommand() {
        return retryOnIllegalArgument(() ->
                RematchCommand.from(inputView.readReMatchingAnswer())
        );
    }

    private MatchingKey readMatchingKey() {
        return retryOnIllegalArgument(() ->
                MatchingKey.from(inputView.readCourseAndMission()));
    }

    private FunctionCommand readCommand() {
        return retryOnIllegalArgument(() ->
                FunctionCommand.from(inputView.readFunction())
        );
    }

    private <T> T retryOnIllegalArgument(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

}
