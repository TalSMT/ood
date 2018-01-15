package Filters;

import java.io.Serializable;

import Sample_Object.SampleOfWifi;

/**
 * 
 * @author Tal and Shaked
 * @param <T>
 * @description - This class contains the condition interface that examines the existence or non-compliance of the test
 */
public interface  Condition extends Serializable{
	boolean test(SampleOfWifi s);
}
