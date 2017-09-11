package d825;

import java.util.HashMap;
import java.util.Map;

public class CanIWin {
	Map<Integer, Boolean> map;
	boolean[] used;

	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
		if (sum < desiredTotal)
			return false;
		if (desiredTotal <= 0)
			return true;

		map = new HashMap<Integer, Boolean>();
		used = new boolean[maxChoosableInteger + 1];
		return helper(desiredTotal);
	}

	public boolean helper(int desiredTotal) {
		if (desiredTotal <= 0)
			return false;
		int key = format(used);
		if (!map.containsKey(key)) {
			for (int i = 1; i < used.length; i++) {
				if (!used[i]) {
					used[i] = true;
					if (!helper(desiredTotal - i)) {
						map.put(key, true);
						used[i] = false;
						return true;
					}
					used[i] = false;
				}
			}
			map.put(key, false);
		}
		return map.get(key);
	}

	public int format(boolean[] used) {
		int num = 0;
		for (boolean b : used) {
			num <<= 1;
			if (b)
				num |= 1;
		}
		return num;
	}

	public static void main(String[] args) {
		CanIWin ciw = new CanIWin();
		System.out.println(ciw.canIWin(10, 11));
	}
}
