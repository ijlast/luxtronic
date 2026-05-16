package enums;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import github.ijl.luxtronic.config.v161.EnumConstantsMap;
import github.ijl.luxtronic.config.v161.EnumIndex;

public class EnumConstantsMapTest {
	@Test(expectedExceptions = NullPointerException.class)
	public void testWithNull() {
		EnumConstantsMap.buildFrom(null);
	}

	// An EnumIndex without any enums should return an emptyMap and not throw
	// exceptions.
	@Test
	public void testWithInterface() {
		EnumIndex dummy = () -> 0;
		Map<Integer, ?> testMap = EnumConstantsMap.buildFrom(dummy.getClass());
		Assert.assertEquals(testMap.size(), 0);
	}

	@Test
	public void testHashMapSizeIsCorrectForCalculations() {
		Map<Integer, TestEnum> testMap = EnumConstantsMap.buildFrom(TestEnum.class);
		Assert.assertEquals(testMap.size(), TestEnum.class.getEnumConstants().length);
	}

	@Test
	public void testHashMapForCalculations() {
		Map<Integer, TestEnum> testMap = EnumConstantsMap.buildFrom(TestEnum.class);
		Assert.assertEquals(testMap.get(TestEnum.ENUM_1.getIntegerValue()), TestEnum.ENUM_1);
	}

	@Test
	public void testHashMapForNonExistingInetegr() {
		Map<Integer, TestEnum> testMap = EnumConstantsMap.buildFrom(TestEnum.class);
		System.out.println(testMap.get(-2));
	}
}
