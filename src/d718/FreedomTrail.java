package d718;

import java.util.HashMap;
import java.util.Map;

public class FreedomTrail {
	// brute force
	// 搜索所有可能性，从两头找，找最小的
	int min = Integer.MAX_VALUE;

	public int findRotateSteps(String ring, String key) {
		if (key.length() == 0 || ring.length() == 0) {
			return 0;
		}
		search(0, ring, key, 0);
		return min;
	}

	public void search(int index, String ring, String key, int len) {
		if (index == key.length()) {
			min = Math.min(min, len);
			return;
		}
		for (int i = 0; i <= ring.length() / 2; i++) {
			if (ring.charAt(i) == key.charAt(index)) {
				search(index + 1, ring.substring(i) + ring.substring(0, i), key, len + i + 1);
			}
		}
		for (int i = ring.length() - 1; i > ring.length() / 2; i--) {
			if (ring.charAt(i) == key.charAt(index)) {
				search(index + 1, ring.substring(i) + ring.substring(0, i), key, len + ring.length() - i + 1);
			}
		}
	}

	//cache,上一步之前最小
	public int findRotateSteps2(String ring, String key) {
		Map<String, Integer> map = new HashMap();
		return dfs(ring, key, 0, map);
	}

	public int dfs(String ring, String key, int index, Map<String, Integer> map) {
		if (index == key.length()) {
			return 0;
		}
		char c = key.charAt(index);
		String hashKey = ring + index;
		if (map.containsKey(hashKey))
			return map.get(hashKey);

		int minSteps = Integer.MAX_VALUE;
		for (int i = 0; i < ring.length(); i++) {
			if (ring.charAt(i) == c) {
				String s = ring.substring(i) + ring.substring(0, i);
				int steps = 1 + Math.min(i, ring.length() - i);
				steps += dfs(s, key, index + 1, map);
				minSteps = Math.min(minSteps, steps);
			}
		}
		map.put(hashKey, minSteps);
		return minSteps;
	}
	
	//2d dp, same as above
	public int findRotateSteps3(String ring, String key) {
        int n = ring.length();
        int m = key.length();
        int[][] dp = new int[m + 1][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (ring.charAt(k) == key.charAt(i)) {
                        int diff = Math.abs(j - k);
                        int step = Math.min(diff, n - diff);
                        dp[i][j] = Math.min(dp[i][j], step + dp[i + 1][k]);
                    }
                }
            }
        }
        return dp[0][0] + m;
    }
}
