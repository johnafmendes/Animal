package br.com.objective.animal.main;

import javax.swing.JOptionPane;

import br.com.objective.animal.service.Proccess;
import br.com.objective.animal.tipo.Type.environment;

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
		
		Proccess p = new Proccess();
		Object[] options = {"OK"};
		while(JOptionPane.OK_OPTION == 
				JOptionPane.showOptionDialog(null,
						"Think in a Animal", "Animals",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   options,
		                   options[0])){
			//água
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "The animal that you thoght live in " + environment.WATER.toString(), "Confirm", JOptionPane.YES_NO_OPTION)){
				//Verifica se há categorias para questionar
				if(p.getRootWater() != null){
					p.checkCharacteristic(environment.WATER.toString());
				} else { //caso não haja caracteristica, pergunta o primeiro animal.
					p.checkFirstAnimal(environment.WATER.toString());
				}
			} else { //terra
				//Verifica se há categorias para questionar
				if(p.getRootLand() != null){
					p.checkCharacteristic(environment.LAND.toString());
				} else { //caso não haja caracteristica, pergunta o primeiro animal.
					p.checkFirstAnimal(environment.LAND.toString());
				}
			}
		}
	}

}
