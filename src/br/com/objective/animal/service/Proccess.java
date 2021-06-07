package br.com.objective.animal.service;

import javax.swing.JOptionPane;

import br.com.objective.animal.model.entidade.Animal;
import br.com.objective.animal.tipo.Type.environment;

/**
 * 
 * @author John Mendes <johnafmendes@gmail.com>
 * @since 15/07/2014
 * @Goal: Classe responsável pelos processamento do jogo
 * @version 1.0
 *
 */
public class Proccess {

	private NodeAnimalCharacteristicLand rootLand;
	private NodeAnimalCharacteristicWater rootWater;
	private Animal animal;
	
	/**
	 * @Goal: Método construtor responsável por iniciar a classe animal
	 * 
	 * @author John Mendes <johnafmendes@gmail.com>
	 * @since 15/07/2014
	 * @version 1.0
	 * 
	 */
	public Proccess(){
		animal = new Animal();
	}
	

	public class NodeAnimalCharacteristicWater {
		NodeAnimalCharacteristicWater leftNot;
		NodeAnimalCharacteristicWater rightYes;
		NodeAnimalCharacteristicWater previous;
		String animal;
		String characteristic;

		public NodeAnimalCharacteristicWater(String animal, String characteristic, NodeAnimalCharacteristicWater auxWater) {
			this.animal = animal;
			this.characteristic = characteristic;
			this.previous = auxWater;
		}
	}
	
	public class NodeAnimalCharacteristicLand {
		NodeAnimalCharacteristicLand leftNot;
		NodeAnimalCharacteristicLand rightYes;
		NodeAnimalCharacteristicLand previous;
		String animal;
		String characteristic;

		public NodeAnimalCharacteristicLand(String animal, String characteristic, NodeAnimalCharacteristicLand auxLand) {
			this.animal = animal;
			this.characteristic = characteristic;
			this.previous = auxLand;
		}
	}

	/**
	 * @Goal: Método responsável por veriricar e incluir o primeiro animal
	 * 
	 * @author John Mendes <johnafmendes@gmail.com>
	 * @since 15/07/2014
	 * @version 1.0
	 * 
	 */
	public void checkFirstAnimal(String type) {
		if(type.equals(environment.WATER.toString())){
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "The animal that you thought is a " + animal.getSHARK(), "The Animal is?", JOptionPane.YES_NO_OPTION)){
				JOptionPane.showMessageDialog(null, "Matched!!!");
			} else { //caso não seja o primeiro animal
				insertAnimalCharacteristic(type, null, null, animal.getSHARK());
			}
		} else { //Land
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "The animal that you thought is a " + animal.getMONKEY(), "The Animal is?", JOptionPane.YES_NO_OPTION)){
				JOptionPane.showMessageDialog(null, "Matched!!!");
			} else { //caso não seja o primeiro animal
				insertAnimalCharacteristic(type, null, null, animal.getMONKEY());
			}
		}
	}

	/**
	 * @Goal: Método responsável por incluir o animal e sua característica
	 * 
	 * @author John Mendes <johnafmendes@gmail.com>
	 * @since 15/07/2014
	 * @version 1.0
	 * 
	 */
	private Object insertAnimalCharacteristic(String type, NodeAnimalCharacteristicWater auxWater, 
			NodeAnimalCharacteristicLand auxLand, String animalPrevious) {
		String animalTemp = JOptionPane.showInputDialog(null, "What is the animal that you thought?");
			
		if(type.equals(environment.WATER.toString())){
			String characteristicTemp = JOptionPane.showInputDialog(null, "A " + animalTemp + " _______________ but a " + animalPrevious + " not.");		
			if(rootWater == null){
				rootWater = new NodeAnimalCharacteristicWater(animalTemp, characteristicTemp, auxWater);
			} else {
				return new NodeAnimalCharacteristicWater(animalTemp, characteristicTemp, auxWater);
			}
			
		} else { //land
			String characteristicTemp = JOptionPane.showInputDialog(null, "A " + animalTemp + " _______________ but a " + animalPrevious + " not.");		
			if(rootLand == null){
				rootLand = new NodeAnimalCharacteristicLand(animalTemp, characteristicTemp, auxLand);
			} else {
				return new NodeAnimalCharacteristicLand(animalTemp, characteristicTemp, auxLand);
			}
		}
		return null;		
	}

	/**
	 * @Goal: Método responsável por percorrer as características e identificar qual animal pertence
	 * 
	 * @author John Mendes <johnafmendes@gmail.com>
	 * @since 15/07/2014
	 * @version 1.0
	 * 
	 */
	public void checkCharacteristic(String tipo) {
		if(tipo.equals(environment.WATER.toString())){
			searchTreeCharacteristicWater(rootWater, tipo, false, animal.getSHARK());
		} else { //terra
			searchTreeCharacteristicLand(rootLand, tipo, false, animal.getMONKEY());
		}
	}

	/**
	 * @Goal: Método responsável por percorrer a árvore com animais e características e incluir quando necessário
	 * 
	 * @author John Mendes <johnafmendes@gmail.com>
	 * @since 15/07/2014
	 * @version 1.0
	 * 
	 */
	private void searchTreeCharacteristicLand(
			NodeAnimalCharacteristicLand aux, String type, boolean previousAnswer,
			String animalCompare) {
		if(aux != null){
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "The animal that you thought " + aux.characteristic, "Is that Characterístic?", JOptionPane.YES_NO_OPTION)){
				if(aux.rightYes == null){
					if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "The animal that you thought is " + aux.animal + "?", "Is that Characterístic?", JOptionPane.YES_NO_OPTION)){
						JOptionPane.showMessageDialog(null, "Matched!!!");
					} else {
						aux.rightYes = (NodeAnimalCharacteristicLand) insertAnimalCharacteristic(type, null, aux, aux.animal);
					}
				} else {
					searchTreeCharacteristicLand(aux.rightYes, type, true, aux.animal);
				}
			} else {
				if(previousAnswer && aux.leftNot == null) {
					if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "The animal that you thought is " + aux.previous.animal + "?", "Is that animal?", JOptionPane.YES_NO_OPTION)){
						JOptionPane.showMessageDialog(null, "Matched!!!");
					} else {
						if(aux.leftNot == null){
							aux.leftNot = (NodeAnimalCharacteristicLand) insertAnimalCharacteristic(type, null, aux, animalCompare);
						} else {
							searchTreeCharacteristicLand(aux.leftNot, type, false, animalCompare);
						}
					} 
				} else {
					if(aux.leftNot != null/* && auxAtual != aux.esquerdaNao*/){
						searchTreeCharacteristicLand(aux.leftNot, type, false, animalCompare);
					} else {//ok
						if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "The animal that you thought is " + animalCompare/*animal.getMONKEY()*/ + "?", "Is that the animal?", JOptionPane.YES_NO_OPTION)){
							JOptionPane.showMessageDialog(null, "Matched!!!");
						} else {
							aux.leftNot = (NodeAnimalCharacteristicLand) insertAnimalCharacteristic(type, null, aux, animalCompare);
						}
					}
				}
			}
		}		
	}

	/**
	 * @Goal: Método responsável por percorrer a árvore com animais e características e incluir quando necessário
	 * 
	 * @author John Mendes <johnafmendes@gmail.com>
	 * @since 15/07/2014
	 * @version 1.0
	 * 
	 */
	private void searchTreeCharacteristicWater(
			NodeAnimalCharacteristicWater aux, String type, boolean previousAnswer,
			String animalCompare) {
		if(aux != null){
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "The animal that you thought " + aux.characteristic, "Is that Characterístic?", JOptionPane.YES_NO_OPTION)){
				if(aux.rightYes == null){
					if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "The animal that you thought is " + aux.animal + "?", "Is that Characterístic?", JOptionPane.YES_NO_OPTION)){
						JOptionPane.showMessageDialog(null, "Matched!!!");
					} else {
						aux.rightYes = (NodeAnimalCharacteristicWater) insertAnimalCharacteristic(type, aux, null, aux.animal);
					}
				} else {
					searchTreeCharacteristicWater(aux.rightYes, type, true, aux.animal);
				}
			} else {
				if(previousAnswer && aux.leftNot == null) {
					if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "The animal that you thought is " + aux.previous.animal + "?", "Is that animal?", JOptionPane.YES_NO_OPTION)){
						JOptionPane.showMessageDialog(null, "Matched!!!");
					} else {
						if(aux.leftNot == null){
							aux.leftNot = (NodeAnimalCharacteristicWater) insertAnimalCharacteristic(type, aux, null, animalCompare);
						} else {
							searchTreeCharacteristicWater(aux.leftNot, type, false, animalCompare);
						}
					} 
				} else {
					if(aux.leftNot != null/* && auxCurrent != aux.leftNot*/){
						searchTreeCharacteristicWater(aux.leftNot, type, false, animalCompare);
					} else {//ok
						if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "The animal that you thought is " + animalCompare /*animal.getSHARK()*/ + "?", "Is that the animal?", JOptionPane.YES_NO_OPTION)){
							JOptionPane.showMessageDialog(null, "Matched!!!");
						} else {
							aux.leftNot = (NodeAnimalCharacteristicWater) insertAnimalCharacteristic(type, aux, null, animalCompare);
						}
					}
				}
			}
		}		
	}

	public NodeAnimalCharacteristicLand getRootLand() {
		return rootLand;
	}

	public void setRaizTerra(NodeAnimalCharacteristicLand raizLand) {
		this.rootLand = raizLand;
	}

	public NodeAnimalCharacteristicWater getRootWater() {
		return rootWater;
	}

	public void setRaizAgua(NodeAnimalCharacteristicWater raizWater) {
		this.rootWater = raizWater;
	}  

}
