package br.com.objective.animal.main;

import javax.swing.JOptionPane;

import br.com.objective.animal.service.Processamento;
import br.com.objective.animal.tipo.Tipo.ambiente;

/**
 * 
 * @author John Mendes <johnafmendes@gmail.com>
 * @since 15/07/2014
 * @Goal: Classe principal responsável pelos acessos aos serviços de processamento
 * @version 1.0
 *
 */
public class AnimalMain {

	/**
	 * @Goal: Método responsável por iniciar a aplicação
	 * 
	 * @author John Mendes <johnafmendes@gmail.com>
	 * @since 15/07/2014
	 * @version 1.0
	 * 
	 */
	public static void main(String[] args) {
		
		Processamento p = new Processamento();
		Object[] options = {"OK"};
		while(JOptionPane.OK_OPTION == 
				JOptionPane.showOptionDialog(null,
						"Pense em um Animal", "Animais",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   options,
		                   options[0])){
			//água
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "O animal que você pensou vive na " + ambiente.AGUA.toString(), "Confirm", JOptionPane.YES_NO_OPTION)){
				//Verifica se há categorias para questionar
				if(p.getRaizAgua() != null){
					p.verificaCaracteristicas(ambiente.AGUA.toString());
				} else { //caso não haja caracteristica, pergunta o primeiro animal.
					p.verificaPrimeiroAnimal(ambiente.AGUA.toString());
				}
			} else { //terra
				//Verifica se há categorias para questionar
				if(p.getRaizTerra() != null){
					p.verificaCaracteristicas(ambiente.TERRA.toString());
				} else { //caso não haja caracteristica, pergunta o primeiro animal.
					p.verificaPrimeiroAnimal(ambiente.TERRA.toString());
				}
			}
		}
	}

}
