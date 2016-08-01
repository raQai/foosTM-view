package de.kongfoos.foostm.view.fx.model.table;

import javax.validation.constraints.NotNull;

import de.kongfoos.foostm.model.table.ATableBuilder;
import de.kongfoos.foostm.model.table.TableType;

public class FXTableBuilderFactory {
    public static FXTable buildTable(@NotNull int id, @NotNull TableType type) {
        return new Builder(id, type).build();
    }

    private static class Builder extends ATableBuilder<FXTable> {
        private Builder(@NotNull int id, @NotNull TableType type) {
            super(id, type);
        }

        @Override
        protected FXTable getInstance() {
            return new FXTable();
        }
    }
}
