CREATE TABLE t_civilite (ID BIGINT NOT NULL, civilite VARCHAR(30) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE t_situation (ID BIGINT NOT NULL, situation VARCHAR(255) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE t_banque (ID BIGINT NOT NULL, banque VARCHAR(255) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE t_type_identite (ID BIGINT NOT NULL, type_identite VARCHAR(255) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE t_type_paiement (ID BIGINT NOT NULL, type_paiement VARCHAR(255) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE t_couleur (ID BIGINT NOT NULL, couleur VARCHAR(255) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE t_manche (ID BIGINT NOT NULL, manche VARCHAR(255) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE t_remboursement (ID BIGINT NOT NULL, libelle_Remboursement VARCHAR(255) NOT NULL, commentaire_remboursement VARCHAR(255), date_remboursement VARCHAR(255), montant_rembourse VARCHAR(255), numero_cheque_remboursement VARCHAR(255), type_remboursement_fk VARCHAR(255), banque_remboursement_fk VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE t_paiement (ID BIGINT NOT NULL, libelle_paiement VARCHAR(255) NOT NULL, commentaire_vente VARCHAR(255), date_vente VARCHAR(255), numero_banque VARCHAR(255), numero_cheque_paiement VARCHAR(255), prix_vente_reel VARCHAR(255), banque_paiement_fk BIGINT, type_paiement_fk BIGINT, type_identite_fk BIGINT NOT NULL, PRIMARY KEY (ID));
-- CREATE TABLE t_paiement (ID BIGINT NOT NULL, libelle_paiement VARCHAR(255) NOT NULL, commentaire_vente VARCHAR(255), date_vente VARCHAR(255), numero_banque VARCHAR(255), numero_cheque_paiement VARCHAR(255), prix_vente_reel VARCHAR(255), banque_paiement_fk VARCHAR(255), type_paiement_fk VARCHAR(255), type_identite_fk VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE t_adresse (ID BIGINT NOT NULL, adresse_1 VARCHAR(255) NOT NULL, adresse_2 VARCHAR(255), code_postal VARCHAR(255) NOT NULL, ville VARCHAR(255) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE t_client (ID BIGINT NOT NULL, deposante BOOLEAN NOT NULL, civilite_fk BIGINT NOT NULL, nom VARCHAR(255), prenom VARCHAR(255), login VARCHAR(255), password VARCHAR(255), adresse_fk BIGINT, telephone_fixe VARCHAR(255), telephone_portable VARCHAR(255), email VARCHAR(255), type_identite_fk BIGINT, numero_identite VARCHAR(255), date_naissance VARCHAR(255), commentaire VARCHAR(255), champ_numerique_1 VARCHAR(255), champ_numerique_2 VARCHAR(255), montant_depose VARCHAR(255), montant_du VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE t_marque (ID BIGINT NOT NULL, name VARCHAR(255) NOT NULL, description VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE t_categorie (ID BIGINT NOT NULL, name VARCHAR(255) NOT NULL, description VARCHAR(255) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE t_categorie_marque (categorie_fk BIGINT, marque_fk BIGINT);
CREATE TABLE t_article (ID BIGINT NOT NULL, version INT NOT NULL, depot_fk BIGINT NOT NULL, client_fk BIGINT NOT NULL, categorie_fk BIGINT NOT NULL, marque_fk BIGINT NOT NULL,couleur_1_fk BIGINT NOT NULL, couleur_2_fk BIGINT NOT NULL, taille VARCHAR(255), manche_fk BIGINT, montant_depot VARCHAR(255) NOT NULL, prix_vente VARCHAR(255) NOT NULL, situation_fk BIGINT NOT NULL, solde VARCHAR(255), pourcentage VARCHAR(255), texte VARCHAR(150), date_depot VARCHAR(255), paiement_fk BIGINT NOT NULL, remboursement_fk BIGINT NOT NULL, PRIMARY KEY (ID));
CREATE TABLE t_depot (ID BIGINT NOT NULL, version INT NOT NULL, client_fk BIGINT NOT NULL, date_depot VARCHAR(255) NOT NULL, cloture_depot VARCHAR(255) NOT NULL, PRIMARY KEY (ID));

ALTER TABLE t_article ADD CONSTRAINT article_depot_fk FOREIGN KEY (depot_fk) REFERENCES t_depot (ID);
ALTER TABLE t_article ADD CONSTRAINT article_client_fk FOREIGN KEY (client_fk) REFERENCES t_client (ID);
ALTER TABLE t_article ADD CONSTRAINT article_categorie_fk FOREIGN KEY (categorie_fk) REFERENCES t_categorie (ID);
ALTER TABLE t_article ADD CONSTRAINT article_marque_fk FOREIGN KEY (marque_fk) REFERENCES t_marque (ID);
ALTER TABLE t_article ADD CONSTRAINT article_couleur_1_fk FOREIGN KEY (couleur_1_fk) REFERENCES t_couleur (ID);
ALTER TABLE t_article ADD CONSTRAINT article_couleur_2_fk FOREIGN KEY (couleur_2_fk) REFERENCES t_couleur (ID);
ALTER TABLE t_article ADD CONSTRAINT article_manche_fk FOREIGN KEY (manche_fk) REFERENCES t_manche (ID);
ALTER TABLE t_article ADD CONSTRAINT article_situation_fk FOREIGN KEY (situation_fk) REFERENCES t_situation (ID);
ALTER TABLE t_article ADD CONSTRAINT article_paiement_fk FOREIGN KEY (paiement_fk) REFERENCES t_paiement (ID);
ALTER TABLE t_article ADD CONSTRAINT article_remboursement_fk FOREIGN KEY (remboursement_fk) REFERENCES t_remboursement (ID);

ALTER TABLE t_depot ADD CONSTRAINT depot_client_fk FOREIGN KEY (client_fk) REFERENCES t_client (ID);

ALTER TABLE t_remboursement ADD CONSTRAINT remboursement_type_fk FOREIGN KEY (type_remboursement_fk) REFERENCES t_type_paiement (ID);
ALTER TABLE t_remboursement ADD CONSTRAINT remboursement_banque_fk FOREIGN KEY (banque_remboursement_fk) REFERENCES t_banque (ID);

ALTER TABLE t_paiement ADD CONSTRAINT paiement_type_fk FOREIGN KEY (type_paiement_fk) REFERENCES t_type_paiement (ID);
ALTER TABLE t_paiement ADD CONSTRAINT paiement_banque_fk FOREIGN KEY (banque_paiement_fk) REFERENCES t_banque (ID);
ALTER TABLE t_paiement ADD CONSTRAINT paiement_type_identite_fk FOREIGN KEY (type_identite_fk) REFERENCES t_type_identite (ID);

ALTER TABLE t_client ADD CONSTRAINT client_civilite_fk FOREIGN KEY (civilite_fk) REFERENCES t_civilite (ID);
ALTER TABLE t_client ADD CONSTRAINT client_adresse_fk FOREIGN KEY (adresse_fk) REFERENCES t_adresse (ID);
ALTER TABLE t_client ADD CONSTRAINT client_type_identite_fk FOREIGN KEY (type_identite_fk) REFERENCES t_type_identite (ID);

ALTER TABLE t_categorie_marque ADD CONSTRAINT categorie_marque_fk FOREIGN KEY (categorie_fk) REFERENCES t_categorie (ID);
ALTER TABLE t_categorie_marque ADD CONSTRAINT categorie_marque_ln_fk FOREIGN KEY (marque_fk) REFERENCES t_marque (ID);

CREATE SEQUENCE depot_sequence START WITH 1 INCREMENT BY 1 MINVALUE 1 NO MAXVALUE NO CYCLE;
CREATE SEQUENCE article_sequence START WITH 1 INCREMENT BY 1 MINVALUE 1 NO MAXVALUE NO CYCLE;

CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL, PRIMARY KEY (SEQ_NAME));
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0);