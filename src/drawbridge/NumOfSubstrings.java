package drawbridge;

public class NumOfSubstrings {
	
	static class SuffixTrieNode{
		SuffixTrieNode[] children = new SuffixTrieNode[26];
		public void insert(String s){
			if(s.length() > 0){
				int index = s.charAt(0) - 'a';
				if(children[index] == null){
					children[index] = new SuffixTrieNode();
				}
				children[index].insert(s.substring(1));
			}
		}
	}
	
	static class SuffixTrie{
		SuffixTrieNode root;
		public SuffixTrie (String s){
			root = new SuffixTrieNode();
			for(int i = 0; i < s.length(); i++){
				root.insert(s.substring(i));
			}
		}
		public int countNodes(SuffixTrieNode root){
			if(root == null){
				return 0;
			}
			int count = 0;
			for(int i = 0; i < 26; i++){
				if(root.children[i] != null){
					count += countNodes(root.children[i]);
				}
			}
			return 1 + count;
		}
		public int countAllNodes(){
			return countNodes(root);
		}
	}
	
	private static int countDistinctSubstring(String s) {
		SuffixTrie trie = new SuffixTrie(s);
		return trie.countAllNodes();
	}
	
	public static void main(String[] args) {
		String s = "kincenvizh";
		
		System.out.println(countDistinctSubstring(s));
	}
}
