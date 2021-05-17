package br.com.objective.animal.service;

import javax.swing.JOptionPane;

import br.com.objective.animal.model.entidade.Animal;
import br.com.objective.animal.tipo.Tipo.ambiente;

/**
 * 
 * @author John Mendes <johnafmendes@gmail.com>
 * @since 15/07/2014
 * @Goal: Classe responsável pelos processamento do jogo
 * @version 1.0
 *
 */
public class Processamento {

	private NoAnimalCaracteristicaTerra raizTerra;
	private NoAnimalCaracteristicaAgua raizAgua;
	private Animal animal;
	
	/**
	 * @Goal: Método construtor responsável por iniciar a classe animal
	 * 
	 * @author John Mendes <johnafmendes@gmail.com>
	 * @since 15/07/2014
	 * @version 1.0
	 * 
	 */
	public Processamento(){
		animal = new Animal();
	}
	

	public class NoAnimalCaracteristicaAgua {
		NoAnimalCaracteristicaAgua esquerdaNao;
		NoAnimalCaracteristicaAgua direitaSim;
		NoAnimalCaracteristicaAgua anterior;
		String animal;
		String caracteristica;

		public NoAnimalCaracteristicaAgua(String animal, String caracteristica, NoAnimalCaracteristicaAgua auxAgua) {
			this.animal = animal;
			this.caracteristica = caracteristica;
			this.anterior = auxAgua;
		}
	}
	
	public class NoAnimalCaracteristicaTerra {
		NoAnimalCaracteristicaTerra esquerdaNao;
		NoAnimalCaracteristicaTerra direitaSim;
		NoAnimalCaracteristicaTerra anterior;
		String animal;
		String caracteristica;

		public NoAnimalCaracteristicaTerra(String animal, String caracteristica, NoAnimalCaracteristicaTerra auxTerra) {
			this.animal = animal;
			this.caracteristica = caracteristica;
			this.anterior = auxTerra;
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
	public void verificaPrimeiroAnimal(String tipo) {
		if(tipo.equals(ambiente.AGUA.toString())){
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "O animal que você pensou é um " + animal.getTUBARAO(), "O Animal é este?", JOptionPane.YES_NO_OPTION)){
				JOptionPane.showMessageDialog(null, "Acertei!!!");
			} else { //caso não seja o primeiro animal
				insereAnimalCaracteristica(tipo, null, null, animal.getTUBARAO());
			}
		} else { //terra
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "O animal que você pensou é um " + animal.getMACACO(), "O Animal é este?", JOptionPane.YES_NO_OPTION)){
				JOptionPane.showMessageDialog(null, "Acertei!!!");
			} else { //caso não seja o primeiro animal
				insereAnimalCaracteristica(tipo, null, null, animal.getMACACO());
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
	private Object insereAnimalCaracteristica(String tipo, NoAnimalCaracteristicaAgua auxAgua, 
			NoAnimalCaracteristicaTerra auxTerra, String animalAnterior) {
		String animalTemp = JOptionPane.showInputDialog(null, "Qual o animal que você pensou?");
			
		if(tipo.equals(ambiente.AGUA.toString())){
			String caracteristicaTemp = JOptionPane.showInputDialog(null, "Um(a) " + animalTemp + " _______________ mas um(a) " + animalAnterior + " não.");		
			if(raizAgua == null){
				raizAgua = new NoAnimalCaracteristicaAgua(animalTemp, caracteristicaTemp, auxAgua);
			} else {
				return new NoAnimalCaracteristicaAgua(animalTemp, caracteristicaTemp, auxAgua);
			}
			
		} else { //terra
			String caracteristicaTemp = JOptionPane.showInputDialog(null, "Um(a) " + animalTemp + " _______________ mas um(a) " + animalAnterior + " não.");		
			if(raizTerra == null){
				raizTerra = new NoAnimalCaracteristicaTerra(animalTemp, caracteristicaTemp, auxTerra);
			} else {
				return new NoAnimalCaracteristicaTerra(animalTemp, caracteristicaTemp, auxTerra);
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
	public void verificaCaracteristicas(String tipo) {
		if(tipo.equals(ambiente.AGUA.toString())){
			percorreArvoreCaracteristicaAgua(raizAgua, tipo, false, animal.getTUBARAO());
		} else { //terra
			percorreArvoreCaracteristicaTerra(raizTerra, tipo, false, animal.getMACACO());
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
	private void percorreArvoreCaracteristicaTerra(
			NoAnimalCaracteristicaTerra aux, String tipo, boolean respostaAnterior,
			String animalComparar) {
		if(aux != null){
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "O animal que você pensou " + aux.caracteristica, "Característica é esta?", JOptionPane.YES_NO_OPTION)){
				if(aux.direitaSim == null){
					if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "O animal que você pensou é " + aux.animal + "?", "Característica é esta?", JOptionPane.YES_NO_OPTION)){
						JOptionPane.showMessageDialog(null, "Acertei!!!");
					} else {
						aux.direitaSim = (NoAnimalCaracteristicaTerra) insereAnimalCaracteristica(tipo, null, aux, aux.animal);
					}
				} else {
					percorreArvoreCaracteristicaTerra(aux.direitaSim, tipo, true, aux.animal);
				}
			} else {
				if(respostaAnterior && aux.esquerdaNao == null) {
					if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "O animal que você pensou é " + aux.anterior.animal + "?", "É este animal?", JOptionPane.YES_NO_OPTION)){
						JOptionPane.showMessageDialog(null, "Acertei!!!");
					} else {
						if(aux.esquerdaNao == null){
							aux.esquerdaNao = (NoAnimalCaracteristicaTerra) insereAnimalCaracteristica(tipo, null, aux, animalComparar);
						} else {
							percorreArvoreCaracteristicaTerra(aux.esquerdaNao, tipo, false, animalComparar);
						}
					} 
				} else {
					if(aux.esquerdaNao != null/* && auxAtual != aux.esquerdaNao*/){
						percorreArvoreCaracteristicaTerra(aux.esquerdaNao, tipo, false, animalComparar);
					} else {//ok
						if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "O animal que você pensou é " + animalComparar/*animal.getMACACO()*/ + "?", "É este o animal?", JOptionPane.YES_NO_OPTION)){
							JOptionPane.showMessageDialog(null, "Acertei!!!");
						} else {
							aux.esquerdaNao = (NoAnimalCaracteristicaTerra) insereAnimalCaracteristica(tipo, null, aux, animalComparar);
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
	private void percorreArvoreCaracteristicaAgua(
			NoAnimalCaracteristicaAgua aux, String tipo, boolean respostaAnterior,
			String animalComparar) {
		if(aux != null){
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "O animal que você pensou " + aux.caracteristica, "Característica é esta?", JOptionPane.YES_NO_OPTION)){
				if(aux.direitaSim == null){
					if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "O animal que você pensou é " + aux.animal + "?", "Característica é esta?", JOptionPane.YES_NO_OPTION)){
						JOptionPane.showMessageDialog(null, "Acertei!!!");
					} else {
						aux.direitaSim = (NoAnimalCaracteristicaAgua) insereAnimalCaracteristica(tipo, aux, null, aux.animal);
					}
				} else {
					percorreArvoreCaracteristicaAgua(aux.direitaSim, tipo, true, aux.animal);
				}
			} else {
				if(respostaAnterior && aux.esquerdaNao == null) {
					if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "O animal que você pensou é " + aux.anterior.animal + "?", "É este animal?", JOptionPane.YES_NO_OPTION)){
						JOptionPane.showMessageDialog(null, "Acertei!!!");
					} else {
						if(aux.esquerdaNao == null){
							aux.esquerdaNao = (NoAnimalCaracteristicaAgua) insereAnimalCaracteristica(tipo, aux, null, animalComparar);
						} else {
							percorreArvoreCaracteristicaAgua(aux.esquerdaNao, tipo, false, animalComparar);
						}
					} 
				} else {
					if(aux.esquerdaNao != null/* && auxAtual != aux.esquerdaNao*/){
						percorreArvoreCaracteristicaAgua(aux.esquerdaNao, tipo, false, animalComparar);
					} else {//ok
						if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "O animal que você pensou é " + animalComparar /*animal.getTUBARAO()*/ + "?", "É este o animal?", JOptionPane.YES_NO_OPTION)){
							JOptionPane.showMessageDialog(null, "Acertei!!!");
						} else {
							aux.esquerdaNao = (NoAnimalCaracteristicaAgua) insereAnimalCaracteristica(tipo, aux, null, animalComparar);
						}
					}
				}
			}
		}		
	}

	public NoAnimalCaracteristicaTerra getRaizTerra() {
		return raizTerra;
	}

	public void setRaizTerra(NoAnimalCaracteristicaTerra raizTerra) {
		this.raizTerra = raizTerra;
	}

	public NoAnimalCaracteristicaAgua getRaizAgua() {
		return raizAgua;
	}

	public void setRaizAgua(NoAnimalCaracteristicaAgua raizAgua) {
		this.raizAgua = raizAgua;
	}  

}
