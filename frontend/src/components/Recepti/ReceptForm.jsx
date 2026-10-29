import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getRecept, createRecept, updateRecept, getKategorije } from "../../services/ReceptService";
import { ZAHTEVNOSTI } from "../../constants";

const PRAZEN = {naslov: "", opis: "", navodila: "", casKuhanja: 0, porcije: 1, kategorijaId: "", sestavine: [{naziv: "", kolicina: 0, enota: ""}], zahtevnost: "SREDNJE"};

export default function ReceptForm(){
    const {id} = useParams();
    const navigate = useNavigate();
    const [forma, setForma] = useState(PRAZEN);
    const [kategorije, setKategorije] = useState([]);

    useEffect(() => {
        getKategorije().then(setKategorije);
    }, []);

    useEffect(() => {
    if (!id) return setForma(PRAZEN);
    getRecept(id).then((r) =>setForma({ ...r, kategorijaId: r.kategorija?.id ?? "", zahtevnost: r.zahtevnost ?? "SREDNJE", }));
    }, [id]);

    const spremeni = (e) => setForma({...forma, [e.target.name]: e.target.value});

    const spremeniSestavino = (i, polje, vrednost) => 
        setForma({...forma,sestavine: forma.sestavine.map((s, j) => (j === i ? { ...s, [polje]: vrednost } : s)),
    });
    
    const dodajSestavino = () =>setForma({ ...forma, sestavine: [...forma.sestavine, { naziv: "", kolicina: 0, enota: "" }] });

    const odstraniSestavino = (i) =>setForma({ ...forma, sestavine: forma.sestavine.filter((_, j) => j !== i) });

    function shrani(e) {
        e.preventDefault();
        const recept = {
            naslov: forma.naslov,
            opis: forma.opis,
            navodila: forma.navodila,
            casKuhanja: Number(forma.casKuhanja),
            porcije: Number(forma.porcije),
            zahtevnost: forma.zahtevnost,
            kategorija: forma.kategorijaId ? { id: Number(forma.kategorijaId) } : null,
            sestavine: forma.sestavine.map((s) => ({
            naziv: s.naziv, kolicina: Number(s.kolicina), enota: s.enota,
      })),
    };
    const zahtevek = id ? updateRecept(id, recept) : createRecept(recept);
    zahtevek.then(() => navigate("/")).catch((err) => 
        alert("Napaka: " + (err.response?.data?.message ?? err.message))
    );
  }
  return (
    <form onSubmit={shrani} className="obrazec">
      <h2>{id ? "Uredi recept" : "Nov recept"}</h2>

      <p><input name="naslov" placeholder="Naslov" value={forma.naslov}onChange={spremeni} required /></p>
      <p><input name="opis" placeholder="Opis" value={forma.opis ?? ""}onChange={spremeni} /></p>

      <p>
        <select name="kategorijaId" value={forma.kategorijaId} onChange={spremeni}>
          <option value="">-- kategorija --</option>
          {kategorije.map((k) => <option key={k.id} value={k.id}>{k.ime}</option>)}
        </select>
      </p>

      <label>
        Zahtevnost:{" "}
        <select name="zahtevnost" value={forma.zahtevnost} onChange={spremeni}>
          {ZAHTEVNOSTI.map((z) => (
            <option key={z.vrednost} value={z.vrednost}>{z.oznaka}</option>
          ))}
        </select>
      </label>
      <p>
        Čas (min): <input name="casKuhanja" type="number" value={forma.casKuhanja} onChange={spremeni} />{" "}
        Porcije: <input name="porcije" type="number" value={forma.porcije} onChange={spremeni} />
      </p>

      <h3>Sestavine</h3>
      {forma.sestavine.map((s, i) => (
        <p key={i} className="vrstica">
          <input placeholder="Naziv" value={s.naziv}onChange={(e) => spremeniSestavino(i, "naziv", e.target.value)} />{" "}
          <input type="number" step="any" placeholder="Količina" value={s.kolicina}onChange={(e) => spremeniSestavino(i, "kolicina", e.target.value)} />{" "}
          <input placeholder="Enota" value={s.enota}onChange={(e) => spremeniSestavino(i, "enota", e.target.value)} />{" "}
          <button type="button" className="gumbBrisi" onClick={() => odstraniSestavino(i)}>✕</button>
        </p>
      ))}
      <button type="button" className="gumb-sekundarni" onClick={dodajSestavino}>+ Dodaj sestavino</button>

      <h3>Navodila</h3>
      <p><textarea name="navodila" rows="5" value={forma.navodila ?? ""} onChange={spremeni} /></p>

      <button type="submit">Shrani</button>
    </form>
  );
}