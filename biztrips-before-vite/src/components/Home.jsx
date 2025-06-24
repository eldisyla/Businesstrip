import React from "react";
import "./Home.css";

export default function Home() {
  return (
    <div className="home-container">
      <h1>Willkommen zur BusinessTrips App</h1>
      <p>
        Diese Webanwendung wurde entwickelt, um die Planung und Verwaltung von
        Geschäftsreisen in einem Unternehmen zu erleichtern.
      </p>
      <p>
        Die App ermöglicht es, <strong>Kunden</strong> und <strong>Mitarbeiter</strong> zu verwalten,
        <strong> Reisen</strong> zu planen, <strong>Hotels</strong> zu buchen und <strong>Leistungen</strong> wie Flüge oder
        Mietwagen zu erfassen. Zudem können <strong>Mitreisende</strong> hinzugefügt und alle Daten über
        <strong> Buchungen</strong> miteinander verknüpft werden.
      </p>
      <p>
        Zielgruppe sind Unternehmen, die eine übersichtliche Lösung für ihre Reiseplanung
        benötigen – intern oder als Dienstleistung für Kunden.
      </p>
      <p><em>Nutze die Navigation oben, um loszulegen.</em></p>
    </div>
  );
}
