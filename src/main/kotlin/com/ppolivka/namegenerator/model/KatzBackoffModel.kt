package com.ppolivka.namegenerator.model

import java.util.*

/**
 * A Markov model built using string training data.
 *
 * @param   data    The training data for the model, an array of words.
 * @param   order   The order of model to use, models of order "n" will look back "n" characters within their context when determining the next letter.
 * @param   prior   The dirichlet prior, an additive smoothing "randomness" factor. Must be in the range 0 to 1.
 * @param   alphabet    The alphabet of the training data i.e. the set of unique symbols used in the training data.
 *
 * @since 1.0
 * @author ppolivka
 */
class KatzBackoffModel(data: List<String>, order: Int, prior: Float, alphabet: List<String>) {

  /**
   * The order of the model i.e. how many characters this model looks back.
   */
  private var order: Int = 2

  /**
   * Dirichlet prior, like additive smoothing, increases the probability of any item being picked.
   */
  private var prior: Float = 1.0f

  /**
   * The alphabet of the training data.
   */
  private var alphabet: List<String> = ArrayList()

  /**
   * The observations.
   */
  private var observations: MutableMap<String, MutableList<String>> = HashMap()

  /**
   * The Markov chains.
   */
  private var chains: MutableMap<String, MutableList<Float>> = HashMap()

  init {
    this.order = order
    this.prior = prior
    this.alphabet = alphabet

    train(data)
    buildChains()
  }

  /**
   * Attempts to generate the next letter in the word given the context (the previous "order" letters).
   * @param   context The previous "order" letters in the word.
   */
  fun generate(context: String): String? {
    val chain = chains[context];
    if (chain == null) {
      return null;
    } else {
      return alphabet[selectIndex(chain)]
    }
  }

  /**
   * Trains the model on the given training data.
   * @param   data    The training data.
   */
  private fun train(data: List<String>) {
    val dataQueue: Queue<String> = LinkedList(data)
    while (dataQueue.size != 0) {
      var d: String = dataQueue.poll()
      d = "#".repeat(order) + d + "#"
      for (i in 0..(d.length - order - 1)) {
        val key = d.substring(i, i + order)
        var value = observations[key]
        if (value == null) {
          value = ArrayList()
          observations[key] = value
        }
        value.add(d.get(i + order).toString())
      }
    }
  }

  /**
   * Builds the Markov chains for the model.
   */
  private fun buildChains() {
    for (context in observations.keys) {
      for (prediction in alphabet) {
        var value = chains[context]
        if (value == null) {
          value = ArrayList()
          chains[context] = value
        }
        value.add(prior + countMatches(observations[context], prediction))
      }
    }
  }

  private fun countMatches(dataSet: List<String>?, value: String): Int {
    if (dataSet == null) {
      return 0;
    }

    var i: Int = 0;
    for (s in dataSet) {
      if (s == value) {
        i++;
      }
    }
    return i;
  }

  private fun selectIndex(chain: List<Float>): Int {
    val totals: MutableList<Float> = ArrayList();
    var accumulator: Float = 0f;

    for (weight in chain) {
      accumulator += weight;
      totals.add(accumulator);
    }

    val rand = Math.random() * accumulator;
    for (i in 0..totals.size) {
      if (rand < totals[i]) {
        return i;
      }
    }

    return 0;
  }

}
