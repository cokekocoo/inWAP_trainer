package tokyo.kenshuhori_in.reflectionEducate;

import java.lang.reflect.Method;

import tokyo.kenshuhori_in.SubMainInterface;

public class ReflectionEducateMain implements SubMainInterface {

	String strClass = "tokyo.kenshuhori_in.reflectionEducate.ReflectionTest";
	String strMethod1 = "setMyName";
	String strMethod2 = "showMyName";

	String myName = "Kenshu Hori";

	@Override
	public void execute() {
		try {
			//クラスの取得
			Class<?> c = Class.forName(strClass);
			//インスタンス生成
			Object myObj = c.newInstance();

			//メソッド(setStr)の取得
			Method method = c.getMethod(strMethod1, String.class);
			//メソッド(setStr)の実行
			method.invoke(myObj, myName);

			method = c.getMethod(strMethod2);
			method.invoke(myObj);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ReflectionTest {

	private String name;

	public ReflectionTest() {
		super();
	}

	public void showMyName() {
		System.out.println("Hello, " + name);
	}

	public void setMyName(String name) {
		this.name = name;
	}
}
