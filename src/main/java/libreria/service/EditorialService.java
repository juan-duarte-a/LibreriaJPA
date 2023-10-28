package libreria.service;

import libreria.entity.Editorial;
import libreria.persistence.EditorialDAO;

public class EditorialService {

    private final EditorialDAO editorialDAO;

    public EditorialService() {
        editorialDAO = new EditorialDAO();
    }

    public void saveEditorial(Editorial editorial) {
        editorialDAO.save(editorial);
    }

    public void removeEditorial(Editorial editorial) {
        editorial.setAlta(false);
        editorialDAO.update(editorial);
    }

    public void updateEditorial(Editorial editorial) {
        editorialDAO.update(editorial);
    }

    public void closeResources() {
        editorialDAO.closeResources();
    }

}
