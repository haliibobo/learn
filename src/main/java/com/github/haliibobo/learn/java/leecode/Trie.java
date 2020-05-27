package com.github.haliibobo.learn.java.leecode;

public class Trie {

    private TrieNode root;

    //节点本身什么没有值，只有边
    static class TrieNode {

        private TrieNode[] links; //边

        private static int R = 26; //最多26个小写字母

        private boolean isEnd; //表示是否有以这个节点结尾的单词

        TrieNode() {
            this.links = new TrieNode[R];
        }

        boolean containsChar(char ch) {
            return links[ch - 'a'] != null;
        }

        TrieNode get(char ch) {
            return links[ch - 'a'];

        }

        //建立一条边
        void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }


        void setEnd() {
            isEnd = true;
        }

        boolean isEnd() {
            return isEnd;
        }
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {

        TrieNode node = root;

        for (int i = 0; i < word.toCharArray().length; i++) {
            char currentChar = word.charAt(i);
            if (!node.containsChar(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }

        //TODO 设置以此节点作为单词结束的标志为true
        node.setEnd();
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.toCharArray().length; i++) {
            char currentChar = word.charAt(i);
            if (node.get(currentChar)== null) {
                return false;
            }
            node = node.get(currentChar);
        }

        //TODO 校验当前节点为结尾的是一个单词还是仅仅是某一个单词的前缀
        return node.isEnd();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {

        TrieNode node = root;

        for (int i = 0; i < prefix.toCharArray().length; i++) {
            char currentChar = prefix.charAt(i);
            if (node.get(currentChar)== null) {
                return false;
            }
            node = node.get(currentChar);
        }

        //直接返回true
        return true;
    }
}
