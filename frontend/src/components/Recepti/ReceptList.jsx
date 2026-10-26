import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getRecepti, deleteRecept, getKategorije } from "../../services/ReceptService";
import { ZAHTEVNOSTI } from "../../constants";

const PRAZNI_FILTRI = { iskanje: "", kategorijaId: "", maxCas: "", zahtevnost: "" };

export default function ReceptList() {
    const [recepti, setRecepti] = useState([]);
    const [kategorije, setKategorije] = useState([]);
    const [filtri, setFiltri] = useState(PRAZNI_FILTRI);

    useEffect(() => {
        getKategorije().then(setKategorije);
    }, []);

    useEffect(() => {
        getRecepti(filtri).then(setRecepti);
    }, [filtri]);

    const spremeniFilter = (e) =>
        setFiltri({ ...filtri, [e.target.name]: e.target.value });

    function izbrisi(id) {
        deleteRecept(id).then(() => setRecepti(recepti.filter((r) => r.id !== id)));
    }

    return (
        <div>
            <div>
                <input
                    name="iskanje"
                    placeholder="Išči po naslovu..."
                    value={filtri.iskanje}
                    onChange={spremeniFilter}
                />

                <select name="kategorijaId" value={filtri.kategorijaId} onChange={spremeniFilter}>
                    <option value="">Vse kategorije</option>
                    {kategorije.map((k) => (
                        <option key={k.id} value={k.id}>{k.ime}</option>
                    ))}
                </select>

                <select name="maxCas" value={filtri.maxCas} onChange={spremeniFilter}>
                    <option value="">Poljuben čas</option>
                    <option value="15">do 15 min</option>
                    <option value="30">do 30 min</option>
                    <option value="60">do 60 min</option>
                    <option value="120">do 120 min</option>
                </select>

                <select name="zahtevnost" value={filtri.zahtevnost} onChange={spremeniFilter}>
                    <option value="">Vse zahtevnosti</option>
                    {ZAHTEVNOSTI.map((z) => (
                        <option key={z.vrednost} value={z.vrednost}>{z.oznaka}</option>
                    ))}
                </select>

                <button type="button" onClick={() => setFiltri(PRAZNI_FILTRI)}>
                    Počisti filtre
                </button>
            </div>

            {recepti.length === 0 && <p>Ni zadetkov.</p>}

            <ul>
                {recepti.map((r) => (
                    <li key={r.id}>
                        <Link to={`/recepti/${r.id}`}>{r.naslov}</Link>{" "}
                        ({r.porcije} porcij){" "}
                        <Link to={`/recepti/${r.id}/uredi`}>Uredi</Link>{" "}
                        <button onClick={() => izbrisi(r.id)}>Izbriši</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}