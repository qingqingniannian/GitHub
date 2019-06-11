import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	/**
	 * Socket�ͻ���
	 */
	
	 /* 
	 * �ͻ���Client.java
		1.����Socket����ָ������˵ĵ�ַ�Ͷ˿ں�
		2.�������Ӻ�ͨ��������������ж�д����
		3.ͨ�������������ȡ������������Ϣ
		4.�ر������Դ
	 * */
	
	public static void main(String[] args) {
		try {
			// ����Socket����
			Socket socket = new Socket("localhost", 8888);

			// ��������������ͷ��������
			OutputStream outputStream = socket.getOutputStream();// ��ȡһ��������������˷�����Ϣ
			PrintWriter printWriter = new PrintWriter(outputStream);// ���������װ�ɴ�ӡ��
			printWriter.print("�������ã�����Balla_����");
			printWriter.flush();
			socket.shutdownOutput();// �ر������

			InputStream inputStream = socket.getInputStream();// ��ȡһ�������������շ���˵���Ϣ
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);// ��װ���ַ��������Ч��
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);// ������
			String info = "";
			String temp = null;// ��ʱ����
			while ((temp = bufferedReader.readLine()) != null) {
				info += temp;
				System.out.println("�ͻ��˽��շ���˷�����Ϣ��" + info);
			}

			// �ر����Ӧ����Դ
			bufferedReader.close();
			inputStream.close();
			printWriter.close();
			outputStream.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
