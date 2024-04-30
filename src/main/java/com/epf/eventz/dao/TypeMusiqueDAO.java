package com.epf.eventz.dao;

import com.epf.eventz.model.TypeMusique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypeMusiqueDAO extends JpaRepository<TypeMusique, Long> {

  /*  public int modifierTypeMusique(TypeMusique typeMusique) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(MODIFY_TYPE_MUSIQUE_QUERY)) {
            preparedStatement.setString(1, typeMusique.getDescription_type_musique());
            preparedStatement.setInt(2, typeMusique.getId_type_musique());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

   */
}


