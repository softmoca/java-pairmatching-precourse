package pairmatching.controller;

import java.util.List;
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
        if (command == FunctionCommand.MACTING) {
            outputView.printCourseAndMission();
            functioningMatching();
            return;
        }

        if (command == FunctionCommand.QUERY) {

            return;
        }

        if (command == FunctionCommand.CLEAR) {

            return;
        }

    }

    private void functioningMatching() {

        MatchingKey matchingKey = readMatchingKey();

        if (matchingHistory.isExist(matchingKey)) {//재매칭
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
        List<Pair> pairs = pairMatchingService.match(matchingKey, matchingHistory);
        outputView.printMatchingResult(pairs);

    }

    private RematchCommand readRematcingCommand() {
        while (true) {
            try {
                String input = inputView.readReMatchingAnswer();
                return RematchCommand.from(input);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }

        }


    }

    private MatchingKey readMatchingKey() {
        while (true) {
            try {
                return MatchingKey.from(inputView.readCourseAndMission());
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }

        }


    }

    private FunctionCommand readCommand() {

        while (true) {
            try {
                String input = inputView.readFunction();
                return FunctionCommand.from(input);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }

        }


    }


}
