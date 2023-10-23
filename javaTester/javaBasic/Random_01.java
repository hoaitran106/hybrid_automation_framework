package javaBasic;

import java.util.Random;

public class Random_01 {
	public static void main(String[] args) {
		int a[] = {1,2,3,4,5,6,7,8,9};
		rotate(a, 3);
	}
	
	public String getEmailRandom() {
		Random rand = new Random();
		return "join" + rand.nextInt(99999) + "@kennedy.us";
	}
	
	//Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
	//Input: nums = [1,2,3,4,5,6,7], k = 3
	//Output: [5,6,7,1,2,3,4]
	public static void rotate(int[] nums, int k) {
        int index = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            index = i + n - k;
            
            if(index < n){
                System.out.println(nums[index]);
            }else{
                System.out.println(nums[index - n]);
            }
        }
    }
}
