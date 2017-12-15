import org.fnlp.nlp.cn.CNFactory;
import java.util.HashMap;
//CNFactory factory = CNFactory.getInstance("models");
public class HelloWorld {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//System.out.println("Hello, World!");
		CNFactory factory = CNFactory.getInstance("models");
String[] words = factory.seg("关注自然语言处理、语音识别、深度学习等方向的前沿技术和业界动态。");
for(String word: words){
	System.out.print(word + " ");
}
String s0 = "患儿5天前无明显诱因出现发热，最高体温达38.6℃，无寒战、抽搐，大小便正常，曾在当地就诊，给予口服“金刚乙胺、蒲地兰、康复新液、护彤、布洛芬混悬液”及静滴“头孢哌酮舒巴坦、阿糖腺苷、热毒宁”，仍有反复发热，今日来我院就诊，以“发热待查”为诊断收住，发病来，神志清，精神欠佳，食欲一般，大小便。既往史：平素体质一般，无肝炎史、结核史、疟疾史，无手术史、外伤史、输血史、献血史，无食物、药物过敏史。";
String s1 = "头孢哌酮舒巴坦";
HashMap<String, String> result = factory.ner(s0);

	// 显示标注结果
	System.out.println(result);

//System.out.println();
	}

}
