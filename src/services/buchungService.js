const BASE_URL = "http://localhost:8080/api/buchungen";

export async function getBuchungen() {
  const res = await fetch(BASE_URL);
  return res.json();
}

export async function createBuchung(buchung) {
  const res = await fetch(BASE_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(buchung),
  });
  return res.json();
}

export async function deleteBuchung(id) {
  await fetch(`${BASE_URL}/${id}`, {
    method: "DELETE",
  });
}
