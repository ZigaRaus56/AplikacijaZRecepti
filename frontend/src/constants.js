export const ZAHTEVNOSTI = [
    {vrednost: "LAHKO", oznaka: "Lahka zahtevnost"},
    {vrednost: "SREDNJE", oznaka: "Srednja zahtevnost"},
    {vrednost: "ZAHTEVNO", oznaka: "Velika zahtevnost"},
];

export const oznakaZahtevnosti = (v) =>
    ZAHTEVNOSTI.find((z) => z.vrednost === v)?.oznaka ?? "-";