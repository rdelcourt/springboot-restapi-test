package com.devtest.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devtest.api.model.CustomerFolder;

/**
 * Customer folder respository interface to communicate with the DB.
 * No need to implement it because it used standard methods from CrudRepository class
 * @author rdelcourt
 *
 */
@Repository
public interface CustomerFolderRepository extends CrudRepository<CustomerFolder, Long> {

}
