package services;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class FieldTest {
    private Field testFieldDefinite;
    private Field testFieldNotDefinite;

    @Before
    public void setUp() throws Exception {
        this.testFieldDefinite = new Field(15, 1);
        this.testFieldNotDefinite = new Field(13, 0);
    }

    @Test
    public void testGetValueDefinite() throws Exception {
        assertEquals(1, testFieldDefinite.getValue());
    }

    @Test (expected = Exception.class)
    public void testGetValueNotDefinite() throws Exception {
        testFieldNotDefinite.getValue();
    }

    @Test
    public void testPossibleValues() throws Exception {
        List<Integer> list = Arrays.asList(new Integer[] {1,2,3,4,5,6,7,8,9});
        assertEquals(list, testFieldNotDefinite.getPossibleValues());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("get field ID", 13, testFieldNotDefinite.getId());
    }

    @Test
    public void testIsDefinite() throws Exception {
        assertTrue(testFieldDefinite.isDefinite());
    }

    @Test
    public void testIsNotDefinite() throws Exception {
        assertFalse(testFieldNotDefinite.isDefinite());
    }

    @Test
    public void testUpdatePossibleValues() throws Exception {
        Field undefinedField = new Field(22,0);
        Set<Integer> toBeRemoved = new HashSet<>();
        toBeRemoved.addAll(Arrays.asList(new Integer[] {1,2,3,4,5}));
        assertTrue(undefinedField.updatePossibleValues(toBeRemoved));
        assertEquals(Arrays.asList(new Integer[] {6,7,8,9}), undefinedField.getPossibleValues());
    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {
        Field f1 = new Field(13, 0);
        assertEquals(testFieldNotDefinite, f1);
    }
}