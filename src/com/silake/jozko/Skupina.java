package com.silake.jozko;

/**
 * Created by Jozko on 17.8.2015.
 */
public class Skupina {

    boolean kladna;
    int zaciatocny_strom;
    int pocet_stromov;
    int[] stromoradie;
    Skupina dalsia_skupina;

    public Skupina(boolean kladnost, int zaciatocny_strom, int pocet_stromov, int[] stromoradie) {
        this.kladna = kladnost;
        this.zaciatocny_strom = zaciatocny_strom;
        this.pocet_stromov = pocet_stromov;
        this.stromoradie = stromoradie;
    }

    public boolean isKladna() {
        return kladna;
    }

    public void setKladna(boolean je_kladna) {
        this.kladna = je_kladna;
    }

    public int getZaciatocny_strom(){
        return zaciatocny_strom;
    }

    public int getRozdiel_jablk(){
        int vysledok = 0;
        for (int i = 0; i < pocet_stromov ; i++) {
            vysledok = vysledok + stromoradie[zaciatocny_strom + i];

        }
        return vysledok;
    }

    public Skupina getDalsia_skupina() {
        return dalsia_skupina;
    }

    public void setDalsia_skupina(Skupina dalsia_skupina) {
        this.dalsia_skupina = dalsia_skupina;
    }

    public boolean oplati_sa_zlucit() {

        if ((dalsia_skupina == null) || (this.getRozdiel_jablk() < 0)) {
            return false;
        }

        Skupina zaujimava_skupina = dalsia_skupina.getDalsia_skupina();
        if (zaujimava_skupina != null) {
            if (zaujimava_skupina.getRozdiel_jablk() > this.getRozdiel_jablk()) {
                if (this.getRozdiel_jablk() + dalsia_skupina.getRozdiel_jablk() > 0) {
                    return true;
                }
            } else {
                if (zaujimava_skupina.getRozdiel_jablk() + dalsia_skupina.getRozdiel_jablk() > 0) {
                    return true;
                }
            }

        } else {
            return false;
        }
        return false;
    }

    public void zluc(){
            this.pocet_stromov = this.pocet_stromov + dalsia_skupina.pocet_stromov;
            this.dalsia_skupina = dalsia_skupina.getDalsia_skupina();
            this.pocet_stromov = this.pocet_stromov + dalsia_skupina.pocet_stromov;
            this.dalsia_skupina = dalsia_skupina.getDalsia_skupina();
    }


}
