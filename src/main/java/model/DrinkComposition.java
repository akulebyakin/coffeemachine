package model;

public class DrinkComposition {
    private int recipeId;
    private int ingredientId;
    private int amount;

    public DrinkComposition(int recipeId, int ingredientId, int amount) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.amount = amount;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public int getAmount() {
        return amount;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "recipe_id: " + recipeId + ", ingredient_id: " + ingredientId + ", amount: " + amount;
    }
}
