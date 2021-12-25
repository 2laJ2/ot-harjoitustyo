# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne on kolmitasoinen kerrosarkkitehtuuri. Koodin pakkausrakenne on seuraava:

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/pakkausrakenne.png" width="180">

Pakkaus _jasentietokannanhallinta.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän _jasentietokannanhallinta.domain_ sovelluslogiikan ja _jasentietokannanhallinta.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää kolme erillistä näkymää
- kirjautuminen
- uuden käyttäjän luominen
- jäsentietokantavalikko (jäsentietojen haku, luominen, muokkaus ja poisto)

Kukin näkymä on toteutettu omana [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html)-olionaan. Näkymistä vain yksi näkymä kerrallaan on näkyvänä eli on sovelluksessa sijoitettuna [stageen](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html). Käyttöliittymä on rakennettu ohjelman luokassa [jasentietokannanhallinta.ui.JasentietokannanhallintaUi](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/ui/JasentietokannanhallintaUi.java).

Käyttöliittymä ja sovelluslogiikka on pyritty pitämään täysin eristettyinä. Käyttöliittymä ainoastaan kutsuu sopivin parametrein sovelluslogiikan toteuttavan olion _jasentiedotServicen_ metodeja.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat [User](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/domain/User.java) ja [Jasentiedot](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/domain/Jasentiedot.java), jotka kuvaavat sovelluksen käyttäjiä ja käyttäjien luomia jäsentietoja.

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/looginendatamalli.png" width="400">

Toiminnallisista kokonaisuuksista vastaa luokan [JasentiedotService](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/domain/JasentiedotService.java) ainoa olio. Luokassa on kaikille käyttöliittymän toiminnoille oma metodi.
Metodeja ovat esim.
- boolean login(String username)
- boolean createUser(String username, String name, String password)
- void createJasentiedot(int id, String name, String address, String phone, User user)
- boolean createNewMember(String name, String address, String phone)
- boolean deleteMember(String name)
- boolean doesMemberNameExist(String name)
- Jasentiedot findMemberByName(String name)
- String getFoundMemberAddressByName(String name)

_JasentiedotService_ pääsee käsiksi käyttäjiin ja jasentietoihin tietojen tallennuksesta vastaavien, pakkauksessa _jasentietojenhallinta.dao_ sijaitsevien rajapintojen _JasentiedotDao_ ja _UserDao_ toteuttavien luokkien kautta. Luokkien toteutus [injektoidaan](https://en.wikipedia.org/wiki/Dependency_injection)sovelluslogiikalle konstruktorikutsun yhteydessä.

JasentiedotServicen ja ohjelman muiden osien suhdetta kuvaa seuraava luokka/pakkauskaavio:

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/pakkauskaavio.png" width="500">

## Tietojen pysyväistallennus

Pakkauksen _jasentietokannanhallinta.dao_ luokat _FileJasentiedotDao_ ja _FileUserDao_ huolehtivat tietojen tallettamisesta tiedostoihin.

Luokat on toteutettu [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) -suunnittelumallin mukaisesti ja ne voi tarpeen vaatiessa korvata uusilla toteutuksilla, jos sovelluksen datan tallennustapa vaihdetaan. Luokat on eristetty rajapintojen _JasentiedotDao_ ja _UserDao_ taakse, eikä sovelluslogiikka käytä niitä suoraan

Sovelluslogiikkaa testattaessa tätä hyödynnetään käyttämällä testeissä tiedostoon tallentavien DAO-olioiden tilalla keskusmuistiin tallentavia toteutuksia.

### Tiedostot

Sovellus tallettaa käyttäjien ja jäsentietojen tiedot erillisiin tiedostoihin.

Sovelluksen juureen sijoitettu [konfiguraatiotiedosto](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kayttoohje.md#konfiguraatiotiedosto) [config.properties](https://github.com/2laJ2/ot-harjoitustyo/tree/master/config.properties) määrittelee tiedostojen nimet.

Sovellus tallentaa käyttäjät seuraavassa muodossa

```
kkala;Kalle Kala;1234567890;
mtuuli;Meri Tuuli;poutapilvi;

```
eli ensin käyttäjätunnus, sitten puolipisteellä erotettuna käyttäjän nimi, puolipisteellä erotettuna salasana ja lopuksi puolipiste. 

Jäsentiedot tallentavan tiedoston muoto on seuraava

```
1;Jaakko Joki;Jokikatu 6 as 5, 80000 Jokimaa;0503348745;mtuuli
2;Sini Siipi;Kukkakuja 3,50500 Niittykumpu;0407894365;kkala

```
Kentät on erotettu toisistaan puolipisteellä. Ensimmäisenä on jäsenen tunnistenumero (voidaan käyttää jäsennumerona) eli _id_, toisena nimi, kolmantena osoite, neljäntenä puhelinnumero ja viimeisenä jäsentiedon tallentaneen käyttäjän käyttäjätunnus.

### Päätoiminnallisuudet

Seuraavassa kuvataan sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaaviona.

#### käyttäjän kirjautuminen

Kun kirjautumisnäkymässä on kirjoitettu syötekenttiin käyttäjätunnus ja salasana ja klikattu painiketta _loginButton_, sovelluksen kontrolli etenee seuraavalla tavalla:

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/sekvenssikaavioKirjaudu.png" width="750">

Painikkeen painamiseen reagoiva [tapahtumankäsittelijä](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/ui/JasentietokannanhallintaUi.java#L84) kutsuu sovelluslogiikan jasentiedotService metodia [login](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/domain/JasentiedotService.java#L44) antaen parametrina kirjautuneen käyttäjätunnuksen. Sovelluslogiikka selvittää UserDaon avulla, onko käyttäjätunnus olemassa. Jos on, sovelluslogiikka tarkistaa, vastaako kirjautumisen yhteydessä annettu salasana käyttäjän oikeaa salasanaa. Jos salasana on oikea, kirjautuminen onnistuu ja käyttöliittymä vaihtaa näkymäksi _mainScenen_ eli sovelluksen varsinaisen päänäkymän.

#### uuden käyttäjän luominen

Kun uuden käyttäjän luomisnäkymässä on ensin syötetty käyttäjätunnus, joka ei vielä ole käytössä sekä nimi ja salasana ja lopuksi klikattu painiketta _createNewUserButton_, sovelluksen kontrolli etenee seuraavalla tavalla:

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/sekvenssikaavioUusiKayttaja.png" width="750">

[Tapahtumankäsittelijä](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/ui/JasentietokannanhallintaUi.java#L147) kutsuu sovelluslogiikan metodia [createUser](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/domain/JasentiedotService.java#L74) antaen parametrina luotavan käyttäjän tiedot. Sovelluslogiikka selvittää _userDao_:n avulla, onko käyttäjätunnus olemassa. Jos ei ole, on mahdollista luoda uusi käyttäjä annetulla käyttäjätunnuksella. Sovelluslogiikka luo _User_-olion ja tallentaa sen kutsumalla _userDao_:n metodia _create_. Tämän jälkeen käyttöliittymä vaihtaa näkymäksi _loginScenen_ eli kirjautumisnäkymän.

#### jasentiedon luominen

Sovelluksen varsinaisen päänäkymän _createMemberButton_-painikkeen klikkaaminen luo uuden jasentiedon. Sovelluksen kontrolli etenee seuraavalla tavalla:

<img src="https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/dokumentaatio/kuvat/sekvenssikaavioUusiJasen.png" width="750">

[Tapahtumakäsittelijä](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/ui/JasentietokannanhallintaUi.java#L258) kutsuu sovelluslogiikan metodia [createNewMember](https://github.com/2laJ2/ot-harjoitustyo/blob/master/JasentietokannanHallinta/src/main/java/jasentietokannanhallinta/domain/JasentiedotService.java#L91) antaen parametrina luotavan jäsenen tiedot. Sovelluslogiikka luo uuden _Jasentiedot_-olion ja tallentaa sen kutsumalla _jasentiedotDao_:n metodia _create_. 

#### muut toiminnallisuudet

Sama periaate toistuu sovelluksen muissa toiminnallisuuksissa; käyttöliittymän tapahtumakäsittelijä kutsuu sopivaa sovelluslogiikan metodia, sovelluslogiikka päivittää jäsentietoja tai kirjautuneen käyttäjän tilaa. Kun kontrolli palaa käyttöliittymään, aktiivinen käyttöliittymä päivittyy tilanteen mukaiseksi.

## Ohjelman rakenteeseen jääneet heikkoudet

### käyttöliittymä

Graafinen käyttöliittymä on toteutettu määrittelemällä lähes koko käyttöliittymän rakenne luokan _JasentietokannanhallintaUi_ metodissa _start_. Vähintään sovelluksen kaikkien kolmen päänäkymän rakentava koodi olisi syytä erottaa omiksi metodeikseen tai luokiksi. Olisi hyvä kehittää metodi, joka tapahtumakäsittelijän aktivoituessa tyhjentäisi käyttönäkymän tekstikentät ja poistaisi ilmoitukset. Tällöin näitä toimintoja ei tarvitsisi erikseen määritellä jokaisen painikkeen tapahtumakäsittelijälle. Metodien nimeämistä voisi vielä hioa systemaattisemmaksi. Käyttöliittymän rakenteellinen määrittely kannattaisi ehkä korvat FXML-määrittelyllä, jolloin sovelluslogiikan ja tapahtumankäsittelijöiden välinen kommunikointi ei häviäisi GUI-elementtejä rakentavan koodin lomaan.

### DAO-luokat

FileDao-toteutuksiin on jäänyt paljon toisteista ohjelmakoodia, kummassakin on mm. hyvin samankaltainen logiikka tiedoston lukemiseen ja tiedoston kirjoittamiseen. Tämä koodi olisi syytä erottaa omaksi luokakseen. Kenties Dao-toteutusten automaattiset testit vähentäisivät refaktorointiin liittyviä riskejä.

### tietoturva

Sovelluksen tietoturva on nykymittapuulla olematon. Salasanan pystyy selvittämään kokeilemalla eikä ohjelma ole turvallinen verkkoon liitettynä. Sovellus toimii parhaiten pienimuotoisena, arkaluontoisia henkilötietoja sisältämättömänä sähköisenä osoitekirjana koneella, joka ei ole liitettynä verkkoon ja johon on rajoitettu pääsy.

### laajennettavuus

Periaatteessa sovellus voisi metodeja lisäämällä tallentaa käyttäjistä ja jäsenistä enemmän tietoja. Tätä varten tietojen tallennustapa kannattaisi selkeyden vuoksi ehkä muuttaa MySQL-tietokannaksi.


