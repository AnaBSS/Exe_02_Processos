package view;

import javax.swing.JOptionPane;

import controller.KillController;

public class Principal1 {

	public static void main(String[] args) {
		KillController elMatador = new KillController();
		String os = elMatador.os();
		elMatador.taskList();

		
		String[] btns = { "Matar por PID", "Matar por Nome", "Sair" };
		int opt = JOptionPane.showOptionDialog(null,
				"Seu sistema operacional é o " + os + ". Escolha o que fazer a seguir.",
				"Exercício 2 de Processos",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, btns, btns[0]);

		if (opt == 0) {
			String pegaPid = JOptionPane.showInputDialog(null, "Digite o PID do processo que deseja finalizar:");
			int pid = Integer.parseInt(pegaPid);
			elMatador.killPID(pid);
			JOptionPane.showMessageDialog(null, "Processo finalizado com sucesso.");
		} else if (opt == 1) {
			String pegaNome = JOptionPane.showInputDialog(null, "Digite o Nome do processo que deseja finalizar:");
			String nome = pegaNome;
			elMatador.killNome(nome);
			JOptionPane.showMessageDialog(null, "Processo finalizado com sucesso.");
		}

	}

}