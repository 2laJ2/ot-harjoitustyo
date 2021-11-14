package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    @Test
    public void uudenKassapaatteenRahamaaraOikea(){
        assertEquals(100000, paate.kassassaRahaa());
    }
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraOikea(){
        assertEquals(0,paate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void myytyjenEdullistenLounaidenMaaraOikea(){
        assertEquals(0,paate.edullisiaLounaitaMyyty());
    }
    @Test
    public void kateismaksuRiittavaKassanRahamaaraKasvaaOikeinSyoMaukkaasti(){
        paate.syoMaukkaasti(400);
        paate.syoMaukkaasti(420);
        assertEquals(100800,paate.kassassaRahaa());
    }
    @Test
    public void kateismaksuRiittavaOikeaVaihtorahaSyoMaukkaasti(){
        assertEquals(0,paate.syoMaukkaasti(400));
        assertEquals(20,paate.syoMaukkaasti(420));
    }
    @Test
    public void kateismaksuRiittavaMyytyjenLounaidenMaaraKasvaaSyoMaukkaasti(){
        paate.syoMaukkaasti(400);
        paate.syoMaukkaasti(420);
        assertEquals(2,paate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void kateismaksuEiRiitaKassanRahamaaraEiMuutuSyoMaukkaasti(){
        paate.syoMaukkaasti(395);
        assertEquals(100000,paate.kassassaRahaa());
    }
    @Test
    public void kateismaksuEiRiitaPalautaRahatVaihtorahanaSyoMaukkaasti(){
        assertEquals(395,paate.syoMaukkaasti(395));
    }
    @Test
    public void kateismaksuEiRiitaMyytyjenLounaidenMaaraEiMuutuSyoMaukkaasti(){
        paate.syoMaukkaasti(395);
        assertEquals(0,paate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void kateismaksuRiittavaKassanRahamaaraKasvaaOikeinSyoEdullisesti(){
        paate.syoEdullisesti(240);
        paate.syoEdullisesti(250);
        assertEquals(100480,paate.kassassaRahaa());
    }
    @Test
    public void kateismaksuRiittavaOikeaVaihtorahaSyoEdullisesti(){
        assertEquals(0,paate.syoEdullisesti(240));
        assertEquals(20,paate.syoEdullisesti(260));
    }
    @Test
    public void kateismaksuRiittavaMyytyjenLounaidenMaaraKasvaaSyoEdullisesti(){
        paate.syoEdullisesti(240);
        paate.syoEdullisesti(250);
        assertEquals(2,paate.edullisiaLounaitaMyyty());
    }
    @Test
    public void kateismaksuEiRiitaKassanRahamaaraEiMuutuSyoEdullisesti(){
        paate.syoEdullisesti(220);
        assertEquals(100000,paate.kassassaRahaa());
        
    }
    @Test
    public void kateismaksuEiRiitaPalautaRahatVaihtorahanaSyoEdullisesti(){
        assertEquals(220,paate.syoEdullisesti(220));
    }
    @Test
    public void kateismaksuEiRiitaMyytyjenLounaidenMaaraEiMuutuSyoEdullisesti(){
        paate.syoEdullisesti(220);
        assertEquals(0,paate.edullisiaLounaitaMyyty());
    }
    @Test
    public void korttiostoTarpeeksiRahaaVeloitaOikeinSyoMaukkaasti(){//veloita summa kortilta ja palauta true
        paate.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo()==600);
    }
    @Test
    public void korttiostoTarpeeksiRahaaMyydytLounaatKasvavatSyoMaukkaasti(){
        paate.syoMaukkaasti(kortti);
        assertEquals(1,paate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void korttiostoTarpeeksiRahaaMyydytLounaatKasvavatSyoMaukkaasti2(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        kortti.lataaRahaa(200);
        paate.syoMaukkaasti(kortti);
        assertEquals(3,paate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void korttiostoEiOleTarpeeksiRahaaSyoMaukkaasti(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(200,kortti.saldo());//kortin rahamäärä ei muutu
        assertEquals(2,paate.maukkaitaLounaitaMyyty());//myytyjen lounaiden määrä ei muutu
        assertFalse(paate.syoMaukkaasti(kortti));//palauta false, jos osto ei onnistu
    }
    @Test
    public void korttiostoTarpeeksiRahaaKassanRahamaaraEiMuutuSyoMaukkaasti(){
        paate.syoMaukkaasti(kortti);
        assertEquals(100000,paate.kassassaRahaa());
    }
    @Test
    public void korttiostoEiOleTarpeeksiRahaaKassanRahamaaraEiMuutuSyoMaukkaasti(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(100000,paate.kassassaRahaa());
    }
    @Test
    public void korttiostoTarpeeksiRahaaVeloitaOikeinSyoEdullisesti(){//veloita summa kortilta ja palauta true
        paate.syoEdullisesti(kortti);
        assertTrue(kortti.saldo()==760);
    }
    @Test
    public void korttiostoTarpeeksiRahaaMyydytLounaatKasvavatSyoEdullisesti(){
        paate.syoEdullisesti(kortti);
        assertEquals(1,paate.edullisiaLounaitaMyyty());
    }
    @Test
    public void korttiostoTarpeeksiRahaaMyydytLounaatKasvavatSyoEdullisesti2(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        kortti.lataaRahaa(40);
        paate.syoEdullisesti(kortti);
        assertEquals(1,paate.edullisiaLounaitaMyyty());
    }
    @Test
    public void korttiostoEiOleTarpeeksiRahaaSyoEdullisesti(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        paate.syoEdullisesti(kortti);
        assertEquals(200,kortti.saldo());//kortin rahamäärä ei muutu
        assertEquals(0,paate.edullisiaLounaitaMyyty());//myytyjen lounaiden määrä ei muutu
        assertFalse(paate.syoEdullisesti(kortti));//palauta false, jos osto ei onnistu
    }
    @Test
    public void korttiostoTarpeeksiRahaaKassanRahamaaraEiMuutuSyoEdullisesti(){
        paate.syoEdullisesti(kortti);
        assertEquals(100000,paate.kassassaRahaa());
    }
    @Test
    public void korttiostoEiOleTarpeeksiRahaaKassanRahamaaraEiMuutuSyoEdullisesti(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        paate.syoEdullisesti(kortti);
        assertEquals(100000,paate.kassassaRahaa());
    }
    @Test
    public void korttiaLadatessaKortinSaldoMuuttuuKassanRahamaaraKasvaaOikein(){
        kortti.lataaRahaa(500);
        assertEquals(1500,kortti.saldo());
        paate.lataaRahaaKortille(kortti, 500);
        assertEquals(100500,paate.kassassaRahaa());
    }
    @Test
    public void korttiaLadatessaKortinSaldoMuuttuuKassanRahamaaraKasvaaOikein2(){
        kortti.lataaRahaa(0);
        assertEquals(1000,kortti.saldo());
        paate.lataaRahaaKortille(kortti, 0);
        assertEquals(100000,paate.kassassaRahaa());
    }
    @Test
    public void korttiaLadatessaKortinSaldoMuuttuuKassanRahamaaraKasvaaOikein3(){
        paate.lataaRahaaKortille(kortti, -500);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000,paate.kassassaRahaa());
    }
}
