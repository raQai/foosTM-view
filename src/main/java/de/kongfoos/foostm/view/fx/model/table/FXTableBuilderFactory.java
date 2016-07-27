package de.kongfoos.foostm.view.fx.model.table;

import de.kongfoos.foostm.model.table.TableBuilder;
import de.kongfoos.foostm.model.table.TableType;

import javax.validation.constraints.NotNull;

public class FXTableBuilderFactory {
    public static FXTable buildTable(@NotNull int id, @NotNull TableType type) {
        return new Builder(id, type).build();
    }

    private static class Builder extends TableBuilder<FXTable> {
        private Builder(@NotNull int id, @NotNull TableType type) {
            super(id, type);
        }

        @Override
        protected FXTable getInstance() {
            return new FXTable();
        }
    }
}
