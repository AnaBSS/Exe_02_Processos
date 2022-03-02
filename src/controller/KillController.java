package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class KillController {
	private Process iProcess(String process) {
		try {
			return Runtime.getRuntime().exec(process);
		} catch (Exception e) {
			String msgErro = e.getMessage();
			if (msgErro.contains("740")) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c");
				buffer.append(" ");
				buffer.append(process);
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else {
				System.err.println(msgErro);
			}
			return null;
		}
	}
	

	public String os() {
		return System.getProperty("os.name");
	}
	
	public void taskList() {
		String taskCmd = "";
		if(os().contains("Windows")) {
			taskCmd = "TASKLIST /FO TABLE";
		}
		else if(os().contains ("Linux")) {
			taskCmd = "ps -ef";
		}
		
		Process p = iProcess(taskCmd);
		InputStream fluxo = p.getInputStream();
		InputStreamReader leitor = new InputStreamReader(fluxo, StandardCharsets.UTF_8);
		BufferedReader buffer = new BufferedReader(leitor);
		
		try {
			String linha = buffer.readLine();
			
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
		}catch(IOException e) {
			System.err.println("Comando inválido");
		}
	}
	
	public void killPID(int pid) {
		String pidCmd = "";
		if(os().contains("Windows")) {
			pidCmd = "TASKKILL /F /PID " + pid;
		}
		else if (os().contains("Linux")) {
			pidCmd = "kill -9 " + pid;
		}
		
		iProcess (pidCmd);
	}
	
	public void killNome(String nome) {
		String nomeCmd = "";
		if(os().contains("Windows")) {
			nomeCmd = "TASKKILL /F /IM " + nome;
		}
		else if (os().contains("Linux")) {
			nomeCmd = "pkill -f " + nome;
		}
		
		iProcess (nomeCmd);
	}
	
}