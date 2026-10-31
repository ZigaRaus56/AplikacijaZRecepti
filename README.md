# Aplikacija za recepte

Spletna aplikacija za shranjevanje, urejanje in iskanje kuhinjskih receptov.
Predmet: Razvoj informacijskih sistemov, Univerza v Mariboru. Avtor: Žiga Raus.

## Kazalo
- [O projektu](#o-projektu)
- [Uporabljene tehnologije](#uporabljene-tehnologije)
- [Struktura projekta](#struktura-projekta)
- [Navodila za nameščanje in zagon](#navodila-za-nameščanje-in-zagon)
- [API](#api)
- [Navodila za razvijalce](#navodila-za-razvijalce)
- [Standardi kodiranja](#standardi-kodiranja)

## O projektu
Aplikacija omogoča, da uporabniki recepte hranijo na enem mestu namesto v zvezkih.
Cilj je enostavno dodajanje, pregledovanje, urejanje in brisanje receptov ter hitro iskanje.

**Funkcionalnosti**
- CRUD nad recepti (naslov, opis, sestavine, navodila, čas kuhanja, porcije, zahtevnost, kategorija)
- Kategorije receptov
- **Iskanje** po naslovu recepta
- **Filtriranje** po kategoriji, času kuhanja in zahtevnosti (filtre je mogoče kombinirati)
- Dokumentacija API-ja v Swaggerju

## Uporabljene tehnologije

### Zaledje (`backend`)
| Tehnologija | Verzija |
|---|---|
| Java (JDK) | 25 |
| Spring Boot | 4.1.1 |
| Spring Web MVC, Spring Data JPA, Bean Validation | prek Spring Boot 4.1.1 |
| Hibernate ORM | 7.4.5 |
| PostgreSQL JDBC gonilnik | 42.7.13 |
| springdoc-openapi (Swagger UI) | 3.1.1 |
| Maven (wrapper `mvnw`) | priložen |

### Obličje (`frontend`)
| Tehnologija | Verzija |
|---|---|
| React | ^19.3.1 |
| React Router | ^7.18.4 |
| Axios | ^1.20.0 |
| Vite | ^8.3.4 |
| ESLint | ^10.13.0 |
| Node.js | 22 LTS ali novejši |

### Baza
| Tehnologija | Verzija |
|---|---|
| PostgreSQL | XX |

## Struktura projekta

```
AplikacijaZRecepti/
├── backend/                         Spring Boot aplikacija
│   ├── pom.xml                      odvisnosti in nastavitve prevajanja
│   ├── mvnw, mvnw.cmd               Maven wrapper (Mavena ni treba nameščati)
│   └── src/
│       ├── main/
│       │   ├── java/com/Raus/backend/
│       │   │   ├── BackendApplication.java   vstopna točka
│       │   │   ├── config/                   nastavitve (OpenAPI/Swagger)
│       │   │   ├── controller/               REST končne točke
│       │   │   ├── model/                    JPA entitete in enum
│       │   │   └── repository/               dostop do baze (Spring Data JPA)
│       │   └── resources/application.properties
│       └── test/java/com/Raus/backend/       testi
├── frontend/                        React aplikacija (Vite)
│   ├── .env                         naslov backenda (VITE_BASE_URL)
│   ├── package.json
│   └── src/
│       ├── main.jsx                 vstopna točka
│       ├── App.jsx                  ogrodje in navigacija
│       ├── constants.js             konstante (zahtevnosti)
│       ├── components/
│       │   ├── Recepti/             ReceptList, ReceptDetail, ReceptForm
│       │   └── routing/             Routing.jsx (poti)
│       └── services/                api.js (axios), ReceptService.js (klici na API)
├── docs/                            dokumentacija in slike
├── postman/                         zbirka zahtevkov za Postman
└── README.md
```

| Mapa | Vsebina |
|---|---|
| `controller` | sprejme HTTP zahtevek, kliče repozitorij, vrne odgovor |
| `model` | entitete `Recept`, `Sestavina`, `Kategorija` in enum `Zahtevnost` |
| `repository` | vmesniki JPA za branje in pisanje v bazo |
| `config` | nastavitve (naslov in opis Swaggerja) |
| `components` | React komponente po straneh |
| `services` | komunikacija z backendom (axios) |

## Navodila za nameščanje in zagon

### Predpogoji
- **JDK 25**
- **Node.js 22 LTS ali novejši**
- **PostgreSQL 17**
- **Git**

### 1. Kloniranje
```bash
git clone https://github.com/ZigaRaus56/AplikacijaZRecepti.git
cd AplikacijaZRecepti
```

### 2. Baza
Ustvarite prazno bazo `recepti`
CREATE DATABASE recepti;

Tabele se ustvarijo samodejno ob prvem zagonu backenda (`spring.jpa.hibernate.ddl-auto=update`).

### 3. Zaledje
Geslo uporabnika `postgres` nastavite kot okoljsko spremenljivko in zaženite backend:

```bash
cd backend
export DB_PASSWORD=vase_geslo
# PowerShell:  $env:DB_PASSWORD="vase_geslo"
# cmd:         set DB_PASSWORD=vase_geslo
./mvnw spring-boot:run
```
Backend teče na `http://localhost:8080`. Swagger UI: `http://localhost:8080/swagger-ui.html`.

### 4. Obličje
V drugem terminalu:

```bash
cd frontend
npm install
npm run dev
```
Aplikacija je na `http://localhost:5173`. Preden jo uporabite, dodajte nekaj kategorij v Swaggerju (`POST /api/kategorije`, npr. `{"ime":"Sladice"}`).

### Okoljske spremenljivke
| Spremenljivka | Kje | Privzeto | Pomen |
|---|---|---|---|
| `DB_PASSWORD` | backend (okolje) | `geslo` | geslo uporabnika baze `postgres` |
| `VITE_BASE_URL` | `frontend/.env` | `http://localhost:8080/api` | naslov backenda |

Povezava do baze je v `backend/src/main/resources/application.properties` (`jdbc:postgresql://localhost:5432/recepti`, uporabnik `postgres`).

### Pogoste težave
| Težava | Rešitev |
|---|---|
| `database "recepti" does not exist` | ustvarite bazo (korak 2) |
| `Connection refused` ali timeout na 5432 | PostgreSQL ne teče, zaženite storitev |
| `npm.ps1 cannot be loaded` (PowerShell) | uporabite Git Bash/cmd ali `npm.cmd` |
| CORS napaka v brskalniku | frontend mora teči na `http://localhost:5173` |
| sprememba `.env` ne deluje | znova zaženite `npm run dev` |

## API

Osnovni naslov: `http://localhost:8080/api`. Polna dokumentacija: Swagger UI.

| Metoda | Pot | Opis |
|---|---|---|
| GET | `/recepti` | seznam; neobvezni parametri: `iskanje`, `kategorijaId`, `maxCas`, `zahtevnost` (`LAHKO`, `SREDNJE`, `ZAHTEVNO`) |
| GET | `/recepti/{id}` | en recept (404, če ne obstaja) |
| POST | `/recepti` | nov recept (201) |
| PUT | `/recepti/{id}` | posodobitev (404, če ne obstaja) |
| DELETE | `/recepti/{id}` | brisanje (204) |
| GET | `/kategorije` | seznam kategorij |
| POST | `/kategorije` | nova kategorija |

Primer: `GET /api/recepti?iskanje=pal&maxCas=30&zahtevnost=LAHKO`

## Navodila za razvijalce
1. Repozitorij kloniramo in sledimo navodilom za zagon.
2. **`main` je vedno delujoča različica**, brez neposrednih potiskov.
3. Za vsako spremembo ustvarimo vejo: `feature/<opis>` (nova funkcionalnost) ali `fix/<opis>` (popravek).
   ```bash
   git checkout -b feature/razvrscanje
   ```
4. Commit sporočila so kratka, v povedni obliki, npr. `Dodano razvrščanje receptov`.
5. Pred potiskom preverimo, da se backend zažene in da `npm run lint` ne javi napak.
6. Vejo potisnemo in odpremo **Pull Request** v `main`; po pregledu ga združimo.
7. Gesla se ne objavljajo v repozitorij; geslo baze se poda prek spremenljivke `DB_PASSWORD`.

## Standardi kodiranja


**Java (zaledje)**
- Razredi `PascalCase`, metode in polja `camelCase`, paketi male črke.
- Plastna zgradba: `controller` → `repository` → `model`.
- REST poti pod `/api/...`, ustrezne HTTP kode (200, 201, 204, 404).
- Identifikatorji entitet so tipa `Long`; enumi se shranjujejo kot besedilo (`@Enumerated(EnumType.STRING)`).
- Končne točke so dokumentirane z anotacijami OpenAPI (`@Tag`, `@Operation`, `@ApiResponse`).

**JavaScript / React (obličje)**
- Funkcijske komponente, ena na datoteko, ime datoteke `PascalCase.jsx`.
- Klici na API samo v mapi `services`, ne neposredno v komponentah.
- Naslov backenda prek `import.meta.env.VITE_BASE_URL`, nikoli trdo zapisan.
- Stanja se ne spreminjajo neposredno (spread, `map`, `filter`).
- Kodo preverjamo z `npm run lint` (ESLint).