const baseUrl = import.meta.env.VITE_API_BASE_URL;

// HOTEL
export async function getHotels() {
  const res = await fetch(baseUrl + "hotels");
  if (res.ok) return res.json();
  throw res;
}

export async function createHotel(data) {
  const res = await fetch(baseUrl + "hotels", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  if (res.ok) return res.json();
  throw res;
}

// MITARBEITER
export async function getMitarbeiter() {
  const res = await fetch(baseUrl + "mitarbeiter");
  if (res.ok) return res.json();
  throw res;
}

export async function createMitarbeiter(data) {
  const res = await fetch(baseUrl + "mitarbeiter", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  if (res.ok) return res.json();
  throw res;
}

// LEISTUNGEN
export async function getLeistungen() {
  const res = await fetch(baseUrl + "leistungen");
  if (res.ok) return res.json();
  throw res;
}

export async function createLeistung(data) {
  const res = await fetch(baseUrl + "leistungen", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  if (res.ok) return res.json();
  throw res;
}

// KUNDEN
export async function getKunden() {
  const res = await fetch(baseUrl + "kunden");
  if (res.ok) return res.json();
  throw res;
}

// BUCHUNGEN
export async function getBuchungen() {
  const res = await fetch(baseUrl + "buchungen");
  if (res.ok) return res.json();
  throw res;
}

// BUCHUNGSLEISTUNGEN
export async function getBuchungsLeistungen() {
  const res = await fetch(baseUrl + "buchungsleistungen");
  if (res.ok) return res.json();
  throw res;
}

export async function createBuchungsLeistung(buchungId, leistungId) {
  const res = await fetch(baseUrl + `buchungsleistungen/${buchungId}/${leistungId}`, {
    method: "POST"
  });
  if (res.ok) return res.json();
  throw res;
}

export async function deleteBuchungsLeistung(buchungId, leistungId) {
  const res = await fetch(baseUrl + `buchungsleistungen/${buchungId}/${leistungId}`, {
    method: "DELETE"
  });
  if (res.ok) return res.json();
  throw res;
}
