const BASE_URL = "http://localhost:8080/api/kunden";

export async function getKundenMitMitreisenden() {
  // Holt alle Kunden mit eingebauten Mitreisenden
  const res = await fetch(BASE_URL);
  return res.json();
}

export async function addMitreisender(hauptkundeId, mitreisenderId) {

  // Fügt einen Mitreisenden zum Hauptkunden hinzu
  const res = await fetch(`${BASE_URL}/${hauptkundeId}/mitreisende`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ mitreisenderId }),
  });
  return res.json();
}

export async function removeMitreisender(hauptkundeId, mitreisenderId) {

  // Entfernt einen Mitreisenden vom Hauptkunden
  await fetch(`${BASE_URL}/${hauptkundeId}/mitreisende/${mitreisenderId}`, {
    method: "DELETE",
  });
}
