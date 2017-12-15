import org.fnlp.nlp.cn.CNFactory;
import java.util.HashMap;
//CNFactory factory = CNFactory.getInstance("models");
public class HelloWorld {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//System.out.println("Hello, World!");
		CNFactory factory = CNFactory.getInstance("models");
String[] words = factory.seg("我去祖国的天安门");
for(String word: words){
	System.out.print(word + " ");
}
String s0 = "幼儿园的时候我妈给我哼的安眠曲，还有两首是爱拼才会赢和舞女泪，老妈到底遭受了些什么";
String s1 = "其实许冠杰的歌词是最讲究押韵的，个人认为押韵是粤语歌的精华";
HashMap<String, String> result = factory.ner(s0);

	// 
	System.out.println(result);

//System.out.println();
	}

}
