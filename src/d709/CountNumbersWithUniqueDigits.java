package d709;

public class CountNumbersWithUniqueDigits {
	//开始想用dp数组
	//dp数组只保存了每个n的count，没存每个n不能的count
	public int countNumbersWithUniqueDigits(int n) {
		if (n == 0)
			return 1;

		int res = 10;
		int uniqueDigits = 9;
		int availableNumber = 9;
		while (n-- > 1 && availableNumber > 0) {
			uniqueDigits = uniqueDigits * availableNumber;
			res += uniqueDigits;
			availableNumber--;
		}
		return res;
	}
}
