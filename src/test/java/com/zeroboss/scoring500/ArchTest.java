package com.zeroboss.scoring500;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.zeroboss.scoring500");

        noClasses()
            .that()
            .resideInAnyPackage("com.zeroboss.scoring500.service..")
            .or()
            .resideInAnyPackage("com.zeroboss.scoring500.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.zeroboss.scoring500.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
