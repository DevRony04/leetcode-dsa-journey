class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
       // Sort the array to ensure minimum absolute difference
       Arrays.sort(arr);

    int n = arr.length;
    List<List<Integer>> result = new ArrayList<>();

    // Step 1: Find the minimum absolute difference
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < n - 1; i++) {
    min = Math.min(min, Math.abs(arr[i + 1] - arr[i]));
    }

    // Step 2: Collect all pairs having the minimum difference
    for (int i = 0; i < n - 1; i++) {
    if (Math.abs(arr[i + 1] - arr[i]) == min) {
        result.add(Arrays.asList(arr[i], arr[i + 1]));
    }
}

    // Return all pairs with the minimum absolute difference
    return result;
    }
}