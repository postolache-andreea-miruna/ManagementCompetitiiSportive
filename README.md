10 business requirements

1. Aplicatia permite adaugarea, modificarea si afisarea cluburilor din federatie.

2. Aplicatia permite adaugarea, modificarea si afisarea antrenorilor care sunt angajati la cluburile federatiei.

3. Aplicatia permite adaugarea, modificarea si afisarea sportivilor care se afla in responsibilitatea unui antrenor.

4. Aplicatia permite adaugarea, modificarea, afisarea si stergerea tipurilor de competitii.

5. Aplicatia permite adaugarea, modificarea, afisarea competitiilor care vor avea loc.

6. Aplicatia permite stergerea competitiilor.

7. Aplicatia permite adaugarea, modificarea, afisarea probelor care au loc in cadrul competitiilor.

8. Aplicatia permite inscrierea unui sportiv la o proba din cadrul unei competitii, dar si stergerea unei inscrieri.

9. Aplicatia permite afisarea rezultatelor sportivilor/sportivului dorit sub diverse forme.

10. Aplicatia va utiliza o baza de date pentru a stoca datele.

5 main features

1. Gestionarea participarilor sportivilor la competitii

Administratorul dupa ce a introdus date in tabelele: sportiv, competitie, proba (administratorul poate adauga probe si le poate afisa) va putea sa adauge date si in tabelul participa, care presupune asocierea unui sportiv cu o competitie si o proba, ceea ce initial inseamna ca respectivul sportiv este inscris la proba aleasa din cadrul competitiei selectate.

Dupa ce sportivul participa la competitie se vor trece timpii si locurile obtinute la probele din cadrul competitiilor la care a participat prin modificarea datelor din tabel.

In ceea ce priveste afisarea, sunt mai multe tipuri: afisarea rezultatelor grupate dupa competitie pentru un sportiv dat, afisarea rezultatelor (numele probei, timpul si locul obtinut) pentru un sportiv si o competitie data, afisarea rezultatelor grupate dupa proba pentru o competitie data si afisarea celor mai buni timpi obtinuti de un sportiv dat la probele la care a participat.

2. Gestionarea competitiilor

Administratorul poate adauga competitiile care se vor desfasura. Acest proces implica faptul ca in tabelul tip au fost adaugate date. Mai mult administratorul, poate afisa toate tipurile de competitii.

In cazul in care apar modificari legate de taxa de participare sau in ceea ce priveste datele de desfasurare ale competitiei, acestea pot fi modificate de administrator. Mai mult,

cluburile vor fi afisate in doua moduri: filtrate dupa anul in care incepe concursul, dar si ordonate descrescator dupa data de start a competitiei.

3. Gestionarea antrenorilor

Administratorul adauga antrenorii noi veniti,iar in cazul introducerii de date eronate acestea se pot modifica ulterior. De asemenea, in ceea ce priveste afisarea, se vor afisa toti antrenorii pentru un club dat, se vor afisa antrenorii ordonati crescator sau descrescator in functie de nume. Mai mult, se va face o afisare in care antrenorii vor fi filtrati in functie de gen si experienta.

4. Gestionarea sportivilor

Administratorul poate adauga sportivi pe care ii asociaza cu antrenorii la care s-au inscris, iar in cazul de date eronate acestea se pot modifica ulterior. De asemenea, in ceea ce priveste afisarea se vor afisa toti sportivii pentru un antrenor dat si o alta afisare va surprinde toti sportivii unui club dat.

5. Gestionarea cluburilor

Administratorul se ocupa cu introducerea cluburilor, iar in cazul introducerii de date eronate se pot face modificari ulterioare. Se vor afisa toate cluburile, dar si informatiile despre un club dat.

# REST endpoints for all the features defined for the MVP

  <details><summary><h2>Controllers</h2></summary><br/>
<img width="150" alt="image" src="https://github.com/postolache-andreea-miruna/ManagementCompetitiiSportive/assets/79594745/81b5dabd-895b-4a4d-84a6-8d02684643d4">
  </details>
 <details><summary><h2>Example of CRUD operations</h2></summary><br/>
   <details><summary><h3>Get</h3></summary><br/>
<img width="503" alt="Screenshot 2024-01-16 135807" src="https://github.com/postolache-andreea-miruna/ManagementCompetitiiSportive/assets/79594745/d34356aa-0a7e-47ee-8791-7fa526fad688">
   </details>
   <details><summary><h3>Post</h3></summary><br/>
<img width="825" alt="image" src="https://github.com/postolache-andreea-miruna/ManagementCompetitiiSportive/assets/79594745/d1ec15b3-06d0-46d9-849e-6a4a688cff80">
   </details>
 </details>

 # Beans for defining services - One service per feature
 <details><summary><h2>Services</h2></summary><br/>
 <img width="155" alt="image" src="https://github.com/postolache-andreea-miruna/ManagementCompetitiiSportive/assets/79594745/af01e448-7345-45f5-8903-5d242513b8a5">
 </details>
 <details><summary><h2>Service example</h2></summary><br/>
<img width="643" alt="image" src="https://github.com/postolache-andreea-miruna/ManagementCompetitiiSportive/assets/79594745/bfc59d21-c560-4252-b7ab-00919d314966">
 </details>
 
# Beans for defining repositories - One repository per entity
 <details><summary><h2>Entities</h2></summary><br/>
   <img width="107" alt="image" src="https://github.com/postolache-andreea-miruna/ManagementCompetitiiSportive/assets/79594745/cb2eaeb2-2207-4e3c-a718-a453fbdf7299">
 </details>
 
 <details><summary><h2>Repositories</h2></summary><br/>
   <img width="147" alt="image" src="https://github.com/postolache-andreea-miruna/ManagementCompetitiiSportive/assets/79594745/1142890d-94a3-45b9-9571-c2aa08eea4fa">
 </details>
 
<details><summary><h2>Repository example</h2></summary><br/>
 <img width="485" alt="image" src="https://github.com/postolache-andreea-miruna/ManagementCompetitiiSportive/assets/79594745/863ef459-8d2f-4a58-9d9b-0ff5de5c4ed0">
</details>

# TESTS
<details><summary><h2>Tests for controllers</h2></summary><br/>
<img width="234" alt="image" src="https://github.com/postolache-andreea-miruna/ManagementCompetitiiSportive/assets/79594745/76c33f23-47c0-438e-80fa-5bcbc49a12e2">

  <img width="506" alt="image" src="https://github.com/postolache-andreea-miruna/ManagementCompetitiiSportive/assets/79594745/7d3a2871-197b-4fd4-a1a1-272b2ed20603">

</details>

<details><summary><h2>Tests for services</h2></summary><br/>
  <img width="232" alt="image" src="https://github.com/postolache-andreea-miruna/ManagementCompetitiiSportive/assets/79594745/043c5d78-7107-4f9d-a7e0-a529aeacbe30">

<img width="501" alt="image" src="https://github.com/postolache-andreea-miruna/ManagementCompetitiiSportive/assets/79594745/8d61d996-3cf2-42f8-ac0a-b3b228c14f1a">

</details>

# Database
<img width="733" alt="image" src="https://github.com/postolache-andreea-miruna/ManagementCompetitiiSportive/assets/79594745/029002ed-718d-4222-b7ea-a17b4c2acd47">
