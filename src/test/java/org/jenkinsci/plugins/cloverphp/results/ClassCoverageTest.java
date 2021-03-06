package org.jenkinsci.plugins.cloverphp.results;

import org.jenkinsci.plugins.cloverphp.CloverBuildAction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test of ClassCoverage
 * 
 * @author Seiji Sogabe
 */
public class ClassCoverageTest {
    
    ClassCoverage target;
    
    public ClassCoverageTest() {
    }

    @Before
    public void setUp() {
        target = new ClassCoverage();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPreviousResult method, of class ClassCoverage.
     */
    @Test
    public void testGetPreviousResult_NullAction() {
        ClassCoverage mock = spy(target);
        doReturn(null).when(mock).getPreviousCloverBuildAction();
        assertNull(mock.getPreviousResult());
    }

    /**
     * Test of getPreviousResult method, of class ClassCoverage.
     */
    @Test
    public void testGetPreviousResult_NullProjectCoverage() {
        CloverBuildAction cba = mock(CloverBuildAction.class);
        when(cba.getResult()).thenReturn(null);
        
        ClassCoverage mock = spy(target);
        doReturn(cba).when(mock).getPreviousCloverBuildAction();
        
        AbstractClassMetrics result = mock.getPreviousResult();
        assertNull(result);
    }

    /**
     * Test of getPreviousResult method, of class ClassCoverage.
     */
    @Test
    public void testGetPreviousResult_NullFileCoverage() {
        FileCoverage fc = mock(FileCoverage.class);
        when(fc.getURLSafeName()).thenReturn("anyString");
        
        ProjectCoverage pc = mock(ProjectCoverage.class);
        when(pc.findFileCoverage(anyString())).thenReturn(null);
        
        CloverBuildAction cba = mock(CloverBuildAction.class);
        when(cba.getResult()).thenReturn(pc);
        
        ClassCoverage mock = spy(target);
        doReturn(fc).when(mock).getParent();
        doReturn(cba).when(mock).getPreviousCloverBuildAction();
        
        AbstractClassMetrics result = mock.getPreviousResult();
        assertNull(result);
    }

    /**
     * Test of getPreviousResult method, of class ClassCoverage.
     */
    @Test
    public void testGetPreviousResult() {
        ClassCoverage cc = mock(ClassCoverage.class);
        
        FileCoverage fc = mock(FileCoverage.class);
        when(fc.getURLSafeName()).thenReturn("anyString");
        when(fc.findClassCoverage(anyString())).thenReturn(cc);
        
        ProjectCoverage pc = mock(ProjectCoverage.class);
        when(pc.findFileCoverage(anyString())).thenReturn(fc);
        
        CloverBuildAction cba = mock(CloverBuildAction.class);
        when(cba.getResult()).thenReturn(pc);
        
        ClassCoverage mock = spy(target);
        doReturn("anyString").when(mock).getURLSafeName();
        doReturn(fc).when(mock).getParent();
        doReturn(cba).when(mock).getPreviousCloverBuildAction();
        
        AbstractClassMetrics result = mock.getPreviousResult();
        assertNotNull(result);
    }
    
}
