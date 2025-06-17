const BASE_URL = "http://localhost:8080/api/kunden";

export async function getKundenMitMitreisenden() {
  const res = await fetch(BASE_URL);
  return res.json();
}

export async function addMitreisender(hauptkundeId, mitreisenderId) {
  const res = await fetch(`${BASE_URL}/${hauptkundeId}/mitreisende`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ mitreisenderId }),
  });
  return res.json();
}

export async function removeMitreisender(hauptkundeId, mitreisenderId) {
  await fetch(`${BASE_URL}/${hauptkundeId}/mitreisende/${mitreisenderId}`, {
    method: "DELETE",
  });
}
