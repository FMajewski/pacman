package zajecia1;

import java.util.Scanner;

public class pacMan {

	public static void main(String[] args) {

		char plansza[][] = new char[10][10];

		uzupelnijPlansze(plansza);
		losujX(plansza);
		losujM(plansza);
		losujC(plansza);
//		wyswietlPlansze(plansza);
		sterowanieKotem(plansza);

	}

	private static void wyswietlPlansze(char[][] plansza) {
		for (int i = 0; i < plansza.length; i++) {
			for (int j = 0; j < plansza[i].length; j++)
				System.out.print(plansza[i][j] + " ");
			System.out.println();
		}
	}

	private static void losujX(char[][] plansza) {
		int licznikX = 0;
		while (licznikX <= 30) {
			int i = (int) (Math.random() * 10);
			int j = (int) (Math.random() * 10);
			if (plansza[i][j] != 'X') {
				plansza[i][j] = 'X';
				licznikX++;
			}
		}
	}

	private static void losujM(char[][] plansza) {
		boolean czy = true;
		do {
			int i = (int) (Math.random() * 10);
			int j = (int) (Math.random() * 10);
			if (plansza[i][j] != 'X' && plansza[i][j] != 'C') {
				plansza[i][j] = 'M';
				czy = false;
			}
		} while (czy);
	}

	private static void losujC(char[][] plansza) {
		boolean czy = true;
		do {
			int i = (int) (Math.random() * 10);
			int j = (int) (Math.random() * 10);
			if (plansza[i][j] != 'X' && plansza[i][j] != 'M') {
				plansza[i][j] = 'C';
				czy = false;
			}
		} while (czy);
	}

	public static void uzupelnijPlansze(char plansza[][]) {
		for (int i = 0; i < plansza.length; i++)
			for (int j = 0; j < plansza[i].length; j++)
				plansza[i][j] = '_';
	}

	public static void sterowanieKotem(char plansza[][]) {
		wyswietlPlansze(plansza);
		Scanner input = new Scanner(System.in);

		int WspX = 0;
		int WspY = 0;
		int i = 0;
		int j = 0;
		boolean czySzukacDalej = true;
		for (i = 0; i < plansza.length && czySzukacDalej; i++)
			for (j = 0; j < plansza[i].length && czySzukacDalej; j++) {
				if (plansza[i][j] == 'C') {
					WspY = i;
					WspX = j;
					czySzukacDalej = false;
					i--;
					j--;
				}
			}
		
		
		System.out.println("Podaj kierunek:");
		String komenda = input.nextLine();

		if (komenda.equals("W")) {
			WspY--;
		} else if (komenda.equals("S")) {
			WspY++;
		} else if (komenda.equals("A")) {
			WspX--;
		} else if (komenda.equals("D")) {
			WspX++;
		} else {
			System.out.println("Z³y kierunek");
		}
		
		if((WspY >= 0 && WspY<= 9) && (WspX >= 0 && WspX <= 9)) {
			if (plansza[WspY][WspX] == 'X'){
				System.out.println("Z³y ruch");
				sterowanieKotem(plansza);
			} else {
				plansza[i][j] = '_';
				plansza[WspY][WspX] = 'C';
			}
			if (czyJestM(plansza) == false) {
				System.out.println("Wygrana");
				wyswietlPlansze(plansza);
			} else
				sterowanieKotem(plansza);

		}else
			sterowanieKotem(plansza);
		
//		KOMENTARZE DO KODU
//		WTOREK 10
		

		
	}

	private static boolean czyJestM(char[][] plansza) {
		for (int i = 0; i < plansza.length; i++)
			for (int j = 0; j < plansza[i].length; j++) {
				if (plansza[i][j] == 'M') {
					return true;
				}
			}
		return false;
	}

}
