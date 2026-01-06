package pairmatching;

import pairmatching.controller.PairMatchingController;
import pairmatching.repository.NameRepository;
import pairmatching.service.PairMatchingService;
import pairmatching.util.FileLoader;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        FileLoader fileLoader = new FileLoader();
        NameRepository nameRepository = new NameRepository(fileLoader);
        PairMatchingService pairMatchingService = new PairMatchingService(nameRepository);

        PairMatchingController pairMatchingController = new PairMatchingController(inputView, outputView,
                pairMatchingService);
        pairMatchingController.run();
    }
}
