package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.List;
import java.util.stream.Collectors;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private HelperRepository<Helper> helperRepository = new HelperRepository();
    private PresentRepository<Present> presentRepository = new PresentRepository<>();

    Shop shop = new ShopImpl();

    @Override
    public String addHelper(String type, String helperName) {
        switch (type) {
            case "Happy":
                Helper happy = new Happy(helperName);
                helperRepository.add(happy);
                return String.format(ADDED_HELPER, "Happy", helperName);
            case "Sleepy":
                Helper sleepy = new Sleepy(helperName);
                helperRepository.add(sleepy);
                return String.format(ADDED_HELPER, "Sleepy", helperName);
            default:
                throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helperWithName = helperRepository.findByName(helperName);
        if (helperWithName == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        } else {
            Instrument instrument = new InstrumentImpl(power);
            helperWithName.addInstrument(instrument);
        }
        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        presentRepository.add(present);
        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    private static int donePresents = 0;


    @Override
    public String craftPresent(String presentName) {
        List<Helper> helpers = helperRepository.getModels().stream()
                .filter(helper -> helper.getEnergy() > 50).collect(Collectors.toList());
        if (helpers.size() == 0) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }
        int countBrokenInstruments = 0;
        Present present = presentRepository.findByName(presentName);
        for (Helper helper : helpers) {
            shop.craft(present, helper);
            List<Instrument> brokenInstruments = helper.getInstruments()
                    .stream().filter(Instrument::isBroken).collect(Collectors.toList());
            countBrokenInstruments += brokenInstruments.size();
        }
        if (present.isDone()) {
            donePresents++;
        }
        String done = present.isDone() ? "done" : "not done";
        String stringBuilder = String.format(PRESENT_DONE, presentName, done) +
                String.format(COUNT_BROKEN_INSTRUMENTS, countBrokenInstruments);
        return stringBuilder.trim();

    }

    @Override
    public String report() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(donePresents)
                .append(" presents are done!")
                .append(System.lineSeparator())
                .append("Helpers info:").append(System.lineSeparator());
        for (Helper model : helperRepository.getModels()) {
            int count = (int) model.getInstruments().stream().filter(instrument -> !instrument.isBroken()).count();
            stringBuilder.append("Name: ")
                    .append(model.getName())
                    .append(System.lineSeparator())
                    .append("Energy: ").append(model.getEnergy())
                    .append(System.lineSeparator())
                    .append("Instruments: ")
                    .append(count)
                    .append(" not broken left").append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
