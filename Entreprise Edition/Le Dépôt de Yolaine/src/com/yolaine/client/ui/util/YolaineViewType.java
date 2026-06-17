package com.yolaine.client.ui.util;


public enum YolaineViewType {

	CHERCHER {

		@Override
		public String getLabel() {
			return "Chercher";
		}

	},
	CREER {

		@Override
		public String getLabel() {
			return "Créer";
		}

	},CREER_DEPOT {

		@Override
		public String getLabel() {
			return "Créer dépôt";
		}

	},CREER_ARTICLE {

		@Override
		public String getLabel() {
			return "Créer article";
		}

	},VENTE_ARTICLE {

		@Override
		public String getLabel() {
			return "Vente de l'article";
		}

	},REMBOURSEMENT_ARTICLE {

		@Override
		public String getLabel() {
			return "Remboursement de l'article";
		}

	},
	CHERCHER_OU_CREER {

		@Override
		public String getLabel() {
			return "Chercher ou créer";
		}

	},
	LIRE {

		@Override
		public String getLabel() {
			return "Lire uniquement ";
		}

	},
	LIRE_DEPOT {

		@Override
		public String getLabel() {
			return "Lire uniquement le dépôt";
		}

	},LIRE_TYPEIDENTITE {

		@Override
		public String getLabel() {
			return "Lire uniquement le type d'identité";
		}

	},LIRE_ARTICLE {

		@Override
		public String getLabel() {
			return "Lire uniquement l' article";
		}

	},LIRE_COULEUR {

		@Override
		public String getLabel() {
			return "Lire uniquement la couleur";
		}

	},
	MISE_A_JOUR {

		@Override
		public String getLabel() {
			return "Mise à jour";
		}

	},
	MISE_A_JOUR_DEPOT {

		@Override
		public String getLabel() {
			return "Mise à jour du dépôt";
		}

	},
	SUPPRIMER {

		@Override
		public String getLabel() {
			return "Supprimer";
		}

	},
	MISE_A_JOUR_OU_SUPPRIMER {

		@Override
		public String getLabel() {
			return "Mise à jour ou supprimer";
		}

	},
	LISTER {

		@Override
		public String getLabel() {
			return "Lister";
		}
	},
	LISTER_CLIENT {

		@Override
		public String getLabel() {
			return "Lister";
		}
	},
	LISTER_MANCHE {

		@Override
		public String getLabel() {
			return "Lister manches";
		}
	},
	LISTER_DEPOT {

		@Override
		public String getLabel() {
			return "Lister dépôts";
		} 
	},

	LISTER_ARTICLE {

		@Override
		public String getLabel() {
			return "Lister articles";
		} 
	},LISTER_ARTICLEVENDU {

		@Override
		public String getLabel() {
			return "Lister articles vendus";
		} 
	},LISTER_ARTICLEREMBOURSE {

		@Override
		public String getLabel() {
			return "Lister articles remboursés";
		} 
	},LISTER_ARTICLERENDU {

		@Override
		public String getLabel() {
			return "Lister articles rendus";
		} 
	},LISTER_ARTICLESMARQUECATEGORIE {

		@Override
		public String getLabel() {
			return "Lister articles par marque";
		} 
	},LISTER_ARTICLESCATEGORIEMARQUE {

		@Override
		public String getLabel() {
			return "Lister articles par marque";
		} 
	},

	LISTER_COULEUR {

		@Override
		public String getLabel() {
			return "Lister couleurs";
		} 
	},
	LISTER_ARTICLEDEPOT {

		@Override
		public String getLabel() {
			return "Lister articles";
		} 
	},
	LISTER_TYPEIDENTITE {

		@Override
		public String getLabel() {
			return "Lister les types d'identités";
		} 
	},
	LISTER_MARQUE {

		@Override
		public String getLabel() {
			return "Lister les marques";
		} 
	},
	LISTER_CATEGORIE {

		@Override
		public String getLabel() {
			return "Lister les types d'articles";
		}
	},LISTER_MARQUESCATEGORIE {

		@Override
		public String getLabel() {
			return "Lister les types d'articles de la marque";
		}
	},LISTER_CATEGORIESMARQUE {

		@Override
		public String getLabel() {
			return "Lister les marques du type d'article";
		}
	},
	LISTER_TYPEPAIEMENT {

		@Override
		public String getLabel() {
			return "Lister les moyens de paiement";
		}
	},
	LISTER_BANQUE {

		@Override
		public String getLabel() {
			return "Lister les banques";
		} 
	}
;
public abstract String getLabel();

}