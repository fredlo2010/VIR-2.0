package com.vir.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vir.model.Text;
import com.vir.model.Word;
import com.vir.model.WordMatch;
import com.vir.repository.WordRepository;
import com.vir.service.TextProcessorService;
import com.vir.service.WordService;

/**
 * A simple text processor to match a word to the category.
 * 
 * @author Alfredo Lopez
 *
 */
@Service("optimizedTextProcessorService")
@Transactional
public class OptimizedTextProcessorService implements TextProcessorService {

	@Autowired
	private WordRepository wordRepository;

	@Autowired
	@Qualifier("simpleWordService")
	private WordService wordService;

	@Override
	public Text process(String textString) {

		List<String> originalWords = getStrings(textString);
		List<String> cleanValues = originalWords.stream()
				.map(wordService::clean)
				.filter(StringUtils::isNotBlank)
				.collect(Collectors.toList());
			
		List<Word> resultWords = wordRepository.findAllIn(cleanValues);
		Map<String, Word> resultsMap = resultWords.stream().collect(Collectors.toMap(Word::getValue, w -> w));
		List<WordMatch> matches = new ArrayList<>();
		
		// Loop through the word list and clean it
		for (String originalWord : originalWords) {

			String clean = wordService.clean(originalWord);
			WordMatch match = new WordMatch(originalWord);
		
			// if result map contains the word then grab it.
			if (resultsMap.containsKey(clean)) {
				Word w = resultsMap.get(clean);
				match.setCategory(w.getCategory());
				match.setValue(w.getValue());
			}
			matches.add(match);
		}

		Text text = new Text();
		text.setWords(matches);

		return text;
	}

	/**
	 * Gets the list of strings from a text.
	 * 
	 * @param textString
	 *            the string to split
	 * @return A list of strings
	 * 
	 *         Note: We make sure there is no more than two white spaces between the
	 *         words.
	 */
	private List<String> getStrings(String textString) {
		String regex = "[\\n\\r\\s]";
		textString = textString.trim();
		return Arrays.asList(textString.split(regex));
	}
}
