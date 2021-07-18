package github.ijl.luxtronic.format.output;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import github.ijl.luxtronic.format.FormatConverter;

@Service
public class ShutdownCodeConverter extends AbstractMapBasedConverter {
	private static final Object[][] HUMAN_READABLE_VALUES = new Object[][] { { 1, "1: heat pump fault" },
			{ 2, "2: system fault" }, { 3, "3: operating mode second heat generator" }, { 4, "4: power supply lock" },
			{ 5, "5: running defrost (only LW devices)" }, { 6, "6: temperature maximum operating limit" },
			{ 7, "7: temperature minimum operating limit (reversible with LWD possibly shutdown due to frost protection during cooling operation - evaporation temperature too high) long below 0C)" },
			{ 8, "8: Lower operating limit" }, { 9, "9: No requirement" }, };

	public ShutdownCodeConverter() {
		super(HUMAN_READABLE_VALUES);
	}
}
