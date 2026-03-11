import java.util.*;

public class WordLadder {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList){

        Set<String> set = new HashSet<>(wordList);

        Queue<String> queue = new LinkedList<>();

        queue.add(beginWord);

        int level = 1;

        while(!queue.isEmpty()){

            int size = queue.size();

            for(int i=0;i<size;i++){

                String word = queue.poll();

                if(word.equals(endWord))
                    return level;

                char[] arr = word.toCharArray();

                for(int j=0;j<arr.length;j++){

                    char original = arr[j];

                    for(char c='a'; c<='z'; c++){

                        arr[j] = c;

                        String next = new String(arr);

                        if(set.contains(next)){

                            queue.add(next);
                            set.remove(next);
                        }
                    }

                    arr[j] = original;
                }
            }

            level++;
        }

        return 0;
    }

    public static void main(String[] args){

        List<String> words = Arrays.asList("hot","dot","dog","lot","log","cog");

        int result = ladderLength("hit","cog",words);

        System.out.println("Shortest transformation length: " + result);
    }
}
