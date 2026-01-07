package pairmatching;

import pairmatching.controller.PairMatchingController;
import pairmatching.domain.MatchingHistory;
import pairmatching.repository.NameRepository;
import pairmatching.service.PairMatchingService;
import pairmatching.util.FileLoader;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {

    public static void main(String[] args) {

        FileLoader fileLoader = new FileLoader();
        NameRepository nameRepository = new NameRepository(fileLoader);
        MatchingHistory matchingHistory = new MatchingHistory();
        PairMatchingService pairMatchingService = new PairMatchingService(nameRepository, matchingHistory);

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        PairMatchingController controller =
                new PairMatchingController(inputView, outputView, pairMatchingService, matchingHistory);

        controller.run();
    }
}
