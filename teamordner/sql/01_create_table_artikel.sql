-- Table: "ARTIKEL"
-- DROP TABLE "ARTIKEL";

CREATE TABLE "ARTIKEL"
(
  "CODE" character varying(255),
  "BEZEICHNUNG" character varying(255),
  "PREIS" double precision,
  "ARTIKEL_ID" serial NOT NULL,
  CONSTRAINT "ARTIKEL_pkey" PRIMARY KEY ("ARTIKEL_ID")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "ARTIKEL"
  OWNER TO postgres;

