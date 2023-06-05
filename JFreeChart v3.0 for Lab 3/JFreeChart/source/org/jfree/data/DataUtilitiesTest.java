package org.jfree.data;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {

	private KeyedValues keyedValues;
	private double[] arrayCreateNumber;
	private double[][] arrayCreateNumber2D;
	private Values2D values2D;

	@Before
	public void setUp() throws Exception {
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values2D = testValues;
		testValues.addValue(1, 0, 0);
		testValues.addValue(4, 1, 0);
	}

	@After
	public void tearDown() throws Exception {
		values2D = null;
		keyedValues = null;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * calculateColumnTotal
	 */
/////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testValidDataAndColumnColumnTotal() {
		assertEquals("Wrong Sum Returned. It Should be 5.0", 5.0, DataUtilities.calculateColumnTotal(values2D, 0),
				0.0000001d);
	}

	@Test
	public void testValidDataTotalColumnTwo() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(3, 4, 8);
		testValues.addValue(2, 5, 11);

		assertEquals("Wrong sum returned. It should be 9", 9.0, DataUtilities.calculateColumnTotal(testValues, 1),
				0.0000001d);
	}

	@Test
	public void testValidDataTotalColumnTwoDecimalNumber() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(3, 4, 8);
		testValues.addValue(2, 5.4, 11);

		assertEquals("Wrong sum returned. It should be 9.4", 9.4, DataUtilities.calculateColumnTotal(testValues, 1),
				0.0000001d);
	}

	@Test
	public void testValidDataTotalColumnOneNegativeNumber() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(3, 4, 8);
		testValues.addValue(-2, 5.4, 11);

		assertEquals("Wrong sum returned. It should be 1", 1.0, DataUtilities.calculateColumnTotal(testValues, 0),
				0.0000001d);
	}

	@Test
	public void testValidDataTotalColumnInvalid() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(3, 4, 8);
		testValues.addValue(-2.5, 5.4, 11);

		try {
			DataUtilities.calculateColumnTotal(testValues, 55);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}

	@Test
	public void testValidDataTotalColumnInvalidNegativeNumber() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(3, 4, 8);
		testValues.addValue(-2.5, 5.4, 11);

		// Spec says should retuen 0 with invalid input entered
		try {
			DataUtilities.calculateColumnTotal(testValues, -1);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}

	// Null
	@Test
	public void testNullInputCalulateColumnTwoTotal() {
		try {
			DataUtilities.calculateColumnTotal(null, 1);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}

	// Illegal Arguement Exception
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArguementExceptionValidColumn() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(3.4, null, null);
		testValues.addValue(null, null, null);

		try {
			DataUtilities.calculateColumnTotal(testValues, 0);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// Illegal Arguement Exception
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArguementExceptionInvalidValidColumn() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(null, null, null);
		testValues.addValue(null, null, null);

		try {
			DataUtilities.calculateColumnTotal(testValues, 11);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// Illegal Arguement Exception
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArguementExceptionValidInputNegativeColumn() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(null, null, 3);
		testValues.addValue(null, null, 15);

		try {
			DataUtilities.calculateColumnTotal(testValues, -1);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * calculateRowTotal
	 */
/////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testValidInputTotalRowOne() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(3, 4, 8);
		testValues.addValue(2, 5, 11);

		assertEquals("Wrong Sum Returned. It Should be 15.0", 15.0, DataUtilities.calculateRowTotal(testValues, 0),
				0.0000001d);
	}

	@Test
	public void testValidInpuTotaltRowTwo() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(3, 4, 8);
		testValues.addValue(2, 5, 11);

		assertEquals("Wrong sum returned. It should be 18.0", 18.0, DataUtilities.calculateRowTotal(testValues, 1),
				0.0000001d);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTotalRowOneNullValues() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(null, null, null);
		testValues.addValue(-2, 5.4, 11);

		try {
			DataUtilities.calculateRowTotal(testValues, 0);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTotalRowTwoNullValuesRowOne() {

		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(-2, 5.4, 11);
		testValues.addValue(null, null, null);
		try {
			DataUtilities.calculateRowTotal(testValues, 1);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	@Test
	public void testRowTotalInvalidRowNumber() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(3, 4, 8);
		testValues.addValue(-2.5, 5.4, 11);

		try {
			DataUtilities.calculateRowTotal(testValues, 55);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}

	@Test
	public void testRowTotalNegativeRowNumber() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(3, 4, 8);
		testValues.addValue(-2.5, 5.4, 11);

		try {
			DataUtilities.calculateColumnTotal(testValues, -1);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}

	// Null
	@Test
	public void testRowTotalNullInput()

	{
		try {
			DataUtilities.calculateRowTotal(null, 1);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}

	// Illegal Arguement Exception
	@Test(expected = IllegalArgumentException.class)
	public void testSeveralNullValuesValidRow() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(3.4, null, null);
		testValues.addValue(null, null, 11);

		try {
			DataUtilities.calculateColumnTotal(testValues, 0);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// Illegal Arguement Exception
	@Test(expected = IllegalArgumentException.class)
	public void testSeveralNullValuesInvalidRow() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(3.4, null, null);
		testValues.addValue(null, null, 11);

		try {
			DataUtilities.calculateColumnTotal(testValues, 22);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	@Test
	public void testValidInputRowTwoTotalNegativeNumbers() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(3, 4, 8);
		testValues.addValue(-2, -5, -11);

		assertEquals("Wrong sum returned. It should be -18.0", -18.0, DataUtilities.calculateRowTotal(testValues, 1),
				0.0000001d);
	}

	@Test
	public void testValidInputRowOneTotalDecimalNumber() {
		// setup
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(3.3, 4.4, 8.3);
		testValues.addValue(2, 5, 11);

		assertEquals("Wrong sum returned. It should be 18.0", 16.0, DataUtilities.calculateRowTotal(testValues, 0),
				0.0000001d);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * createNumberArray
	 */
/////////////////////////////////////////////////////////////////////////////////////////////////////

	private void createNullArray() {
// setup
		arrayCreateNumber = null;
	}

	private void createSingleValueArray() {
// setup
		arrayCreateNumber = new double[] { 2.0 };
	}

	private void createArray() {
// setup
		arrayCreateNumber = new double[] { 5.0, 10.0, -15.0, 3.0 };
	}

	@Test
	public void testCopySingleValueArrayLength() {
		createSingleValueArray();
		Number[] actual = DataUtilities.createNumberArray(arrayCreateNumber);

		assertEquals("Length of arrays both should be equal.", arrayCreateNumber.length, actual.length);
	}

	@Test
	public void testCopyArrayLength() {
		createArray();
		Number[] actual = DataUtilities.createNumberArray(arrayCreateNumber);

		assertEquals("Length of both arrays should be equal.", arrayCreateNumber.length, actual.length);
	}

	@Test
	public void testArraysDontMatchSameLength() {

		createArray();
		double actualArray[] = { 5, 10, 15, 20 };
		Number[] actual = DataUtilities.createNumberArray(actualArray);

		assertEquals("Length of both arrays should be equal.", arrayCreateNumber.length, actual.length);
	}

	@Test
	public void testArraysDontMatchDifferentLength() {

		createArray();
		double actualArray[] = { 10, 15, 20 };
		Number[] actual = DataUtilities.createNumberArray(actualArray);

		assertEquals("Length of both arrays should be equal.", arrayCreateNumber.length, actual.length);
	}

	@Test
	public void testCopyArrayData() {
		createArray();
		Number[] actual = DataUtilities.createNumberArray(arrayCreateNumber);

		assertEquals("Array contents must be equal.", arrayCreateNumber[0], actual[0].doubleValue(), 0.000000001d);
		assertEquals("Array contents must be equal.", arrayCreateNumber[1], actual[1].doubleValue(), 0.000000001d);
		assertEquals("Array contents must be equal.", arrayCreateNumber[2], actual[2].doubleValue(), 0.000000001d);
	}

	@Test
	public void testThrowsExpectionWhenInputArrayIsNull() {

		createNullArray();

		try {
			DataUtilities.createNumberArray(arrayCreateNumber);
			fail("No exception thrown-Expected outcome was: a thrown exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * createNumberArray2D
	 */
/////////////////////////////////////////////////////////////////////////////////////////////////////

	private void create1DArray() {
//setup
		double[] array1D = { 1.0 };
		arrayCreateNumber2D = new double[][] { array1D };
	}

	private void create2DArray() {
//setup
		double[] first = { 1.0, 2.11, -1.4, 0.0 };
		double[] second = { 4.0, -8.0, 16.0, -24.0 };
		arrayCreateNumber2D = new double[][] { first, second };
	}

	@Test
	public void testCopySingleValue1DArrayLength() {
//setup
		create1DArray();
		Number[][] actual = DataUtilities.createNumberArray2D(arrayCreateNumber2D);

		assertEquals("Length of both arrays should be equal.", arrayCreateNumber2D.length, actual.length);
	}

	@Test
	public void testCopy2DArrayLength() {
// setup
		create2DArray();
		Number[][] actual = DataUtilities.createNumberArray2D(arrayCreateNumber2D);

		assertEquals("Length of both arrays should be equal.", arrayCreateNumber2D.length, actual.length);
	}

	@Test
	public void test2DArraysDontMatchSameLength() {

		create2DArray();
		double actualArray[][] = { { 5, 10, 15, 20 }, { 25, 30, 35, 40 } };
		Number[][] actual = DataUtilities.createNumberArray2D(actualArray);

		assertEquals("Length of both arrays should be equal.", arrayCreateNumber2D.length, actual.length);
	}

	@Test
	public void test2DArraysDontMatchDifferentLength() {

		create2DArray();
		double actualArray[][] = { { 5, 10, 15, 20 }, { 25, 30, 35 } };
		Number[][] actual = DataUtilities.createNumberArray2D(actualArray);

		assertEquals("Length of both arrays should be equal.", arrayCreateNumber2D.length, actual.length);
	}

	@Test
	public void testThrowsExpectionWhenInput2DArrayNull() {

		try {
			DataUtilities.createNumberArray2D(null);
			fail("No exception thrown-Expected outcome was: a thrown exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * getCumulativePercentages
	 */
/////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("rawtypes")
	@Test
	public void testReturnKeyValueTwo() {
		// setup

		DefaultKeyedValues keyValues = new DefaultKeyedValues();
		keyedValues = keyValues;

		keyValues.addValue((Comparable) 0.0, 5.0);
		keyValues.addValue((Comparable) 1.0, 9.0);
		keyValues.addValue((Comparable) 2.0, 2.0);

		KeyedValues test = DataUtilities.getCumulativePercentages((KeyedValues) keyValues);

		assertEquals((double) test.getValue(2), 1.0, .000000001d);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testReturnKeyValueOne() {
		// setup

		DefaultKeyedValues keyValues = new DefaultKeyedValues();
		keyedValues = keyValues;

		keyValues.addValue((Comparable) 0.0, 5.0);
		keyValues.addValue((Comparable) 1.0, 9.0);
		keyValues.addValue((Comparable) 2.0, 2.0);

		KeyedValues test = DataUtilities.getCumulativePercentages((KeyedValues) keyValues);

		DataUtilities.getCumulativePercentages((KeyedValues) keyValues);

		assertEquals((double) test.getValue(1), 0.875, .000000001d);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testReturnKeyValueOneDecimalValue() {
		// setup

		DefaultKeyedValues keyValues = new DefaultKeyedValues();
		keyedValues = keyValues;

		keyValues.addValue((Comparable) 0.0, 2.5);
		keyValues.addValue((Comparable) 1.0, 5.7);
		keyValues.addValue((Comparable) 2.0, 1.8);

		KeyedValues test = DataUtilities.getCumulativePercentages((KeyedValues) keyValues);

		DataUtilities.getCumulativePercentages((KeyedValues) keyValues);

		assertEquals((double) test.getValue(1), 0.82, .000000001d);
	}

	// Invalid Arguement Exception
	@SuppressWarnings("rawtypes")
	@Test
	public void testReturnKeyValueOneDividedByNegativeNumber() {
		// setup

		DefaultKeyedValues keyValues = new DefaultKeyedValues();
		keyedValues = keyValues;

		keyValues.addValue((Comparable) 0.0, 5.0);
		keyValues.addValue((Comparable) 1.0, 9.0);
		keyValues.addValue((Comparable) 2.0, -15.0);

		try {
			KeyedValues test = DataUtilities.getCumulativePercentages((KeyedValues) keyValues);
			test.getValue(1);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}

	// Invalid Arguement Exception
	@SuppressWarnings("rawtypes")
	@Test
	public void testReturnInvalidKeyValue() {
		// setup

		DefaultKeyedValues keyValues = new DefaultKeyedValues();
		keyedValues = keyValues;

		keyValues.addValue((Comparable) 0.0, 5.0);
		keyValues.addValue((Comparable) 1.0, 9.0);
		keyValues.addValue((Comparable) 2.0, -15.0);

		try {
			KeyedValues test = DataUtilities.getCumulativePercentages((KeyedValues) keyValues);
			test.getValue(3);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}

	// Invalid Arguement Exception
	@SuppressWarnings("rawtypes")
	@Test
	public void testNegativeValueLastCumulativePercentageShouldBeOne() {
		// setup

		DefaultKeyedValues keyValues = new DefaultKeyedValues();
		keyedValues = keyValues;

		keyValues.addValue((Comparable) 0.0, -5.0);
		keyValues.addValue((Comparable) 1.0, -9.0);
		keyValues.addValue((Comparable) 2.0, -15.0);

		KeyedValues test = DataUtilities.getCumulativePercentages((KeyedValues) keyValues);

		assertEquals((double) test.getValue(2), 1.0, .000000001d);
	}

	// Null
	@Test
	public void testNullInputInvalidParameterType() {
		try {
			DataUtilities.getCumulativePercentages(null);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullKeyValuesIllegalExperssion() {
		// setup

		DefaultKeyedValues keyValues = new DefaultKeyedValues();
		keyedValues = keyValues;

		keyValues.addValue(null, 5.0);
		keyValues.addValue(null, 9.0);
		keyValues.addValue(null, 2.0);

		try {
			DataUtilities.getCumulativePercentages(keyedValues);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// Invalid Arguement Exception
	@SuppressWarnings("rawtypes")
	@Test
	public void testNullValues() {
		// setup

		DefaultKeyedValues keyValues = new DefaultKeyedValues();
		keyedValues = keyValues;

		keyValues.addValue((Comparable) 0.0, null);
		keyValues.addValue((Comparable) 1.0, null);
		keyValues.addValue((Comparable) 2.0, null);

		try {
			DataUtilities.getCumulativePercentages(keyedValues);
			fail("No exception thrownâ€�Expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}
}
