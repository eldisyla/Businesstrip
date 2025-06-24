const BASE_URL = "http://localhost:8080/api/reisen";

// Normale Reisen abrufen (funktioniert mit dem aktuellen Buchungsformular)
export async function getReisen() {
  const res = await fetch(BASE_URL);
  if (!res.ok) {
    throw new Error("Fehler beim Laden der Reisen.");
  }
  return res.json();
}


export async function getReisenMitMitreisenden() {
  const res = await fetch(`${BASE_URL}/mit-mitreisenden`);
  if (!res.ok) {
    throw new Error("Fehler beim Laden der Reisen mit Mitreisenden.");
  }
  return res.json();
}

// ➕ Neue Reise erstellen
export async function createReise(reise) {
  const res = await fetch(BASE_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(reise),
  });

  if (!res.ok) {
    const error = await res.json();
    console.error("Fehler beim Erstellen der Reise:", error);
    throw new Error("Fehler beim Erstellen der Reise.");
  }

  return res.json();
}

// Reise löschen
export async function deleteReise(id) {
  const res = await fetch(`${BASE_URL}/${id}`, {
    method: "DELETE",
  });

  if (!res.ok) {
    throw new Error(`Fehler beim Löschen der Reise mit ID ${id}.`);
  }
}
